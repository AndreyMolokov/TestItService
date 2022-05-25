package ru.testitservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.testitservice.dao.TaskDataDao;
import ru.testitservice.entity.TaskData;
import ru.testitservice.model.TaskModel;
import ru.testitservice.service.FileService;
import ru.testitservice.service.TaskResolver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/main")
public class MainController {
    private final TaskResolver taskResolver;

    private final TaskDataDao taskDataDao;

    @Autowired
    public MainController(TaskDataDao taskDataDao, TaskResolver taskResolver) {
        this.taskDataDao = taskDataDao;
        this.taskResolver = taskResolver;
    }

    String outputmesseg;

    @GetMapping()
    public String newTask(@ModelAttribute("task") TaskModel taskModel, Model model) {
        model.addAttribute("ans", outputmesseg);
        List<TaskData> taskDataList = taskDataDao.findAll();

        model.addAttribute("tasklist", taskDataList);
        return "main";
    }

    @PostMapping(params = "action=Calculate")
    public String calculate(@ModelAttribute TaskModel taskModel, Model model) {
        String ans = taskResolver.validTask(taskModel);
        if (ans == null) {
            ans = taskResolver.resolveTask(taskModel);
            taskDataDao.create(taskModel);
        }


        outputmesseg = ans;
        return "redirect:/main";
    }

    @PostMapping(params = "action=Save")
    public String save(@ModelAttribute("task") TaskModel taskModel, Model model) {
        String ans = taskResolver.validTask(taskModel);
        if (ans == null) {
            ans = taskResolver.resolveTask(taskModel);
            taskDataDao.create(taskModel);
        }
        outputmesseg = ans;
        return "redirect:/main";
    }
    @ResponseBody
    @PostMapping(params = "action=Export")
    public ResponseEntity<InputStreamResource> export(@ModelAttribute("task") TaskModel taskModel, Model model) throws IOException {



        String path = System.getProperty("user.dir");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String filename = timeStamp+".txt";
        File file = new File(filename);

        String relativePath = path + "\\" +filename;
        file = new File(relativePath);
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(taskModel.toString());

        writer.close();
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .contentLength(file.length()) //
                .body(resource);

    }


    @PostMapping(params = "action=Import")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        String s = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining(""));
        System.out.println(s);
        try {
            TaskModel taskModel = FileService.StringToTaskModel(s);
            String ans = taskResolver.validTask(taskModel);
            if (ans == null) {
                ans = taskResolver.resolveTask(taskModel);
                taskDataDao.create(taskModel);
                outputmesseg = ans;
            }
        }catch (IllegalArgumentException e){
            outputmesseg="incorrect file";
        }




        return "redirect:/main";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, @ModelAttribute("task") TaskModel taskModel, Model model) {

        TaskModel taskModel1 = taskDataDao.read(id).toTaskModel();
        String ans = taskResolver.validTask(taskModel1);
        if (ans == null) {
            ans = taskResolver.resolveTask(taskModel1);
        }
        model.addAttribute("currentModel", taskModel1);
        model.addAttribute("ans", outputmesseg);
        List<TaskData> taskDataList = taskDataDao.findAll();

        model.addAttribute("tasklist", taskDataList);

        outputmesseg = ans;
        return "redirect:/main";
    }

}
