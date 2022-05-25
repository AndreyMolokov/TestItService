package ru.testitservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.testitservice.model.TaskModel;
import ru.testitservice.tasktype.TaskType;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class TaskResolver {
    private HashMap<TaskType,TaskResolveSemiService> map;
    @Autowired
    public TaskResolver(List<TaskResolveSemiService> list) {
        map=new HashMap<>();
        for (TaskResolveSemiService t:list){
            map.put(t.getTaskType(),t);
        }

    }


    public String resolveTask(TaskModel taskModel){
        return  map.get(taskModel.getTaskType()).processing(taskModel.getTaskText());
    }
    public String validTask(TaskModel taskModel){
        try {
            String validans=map.get(taskModel.getTaskType()).validate(taskModel.getTaskText());
            return validans;
        }catch (Exception e){
            return "incorrect data";
        }
    }

}
