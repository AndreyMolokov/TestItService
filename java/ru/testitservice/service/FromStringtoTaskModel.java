package ru.testitservice.service;

import ru.testitservice.model.TaskModel;
import ru.testitservice.tasktype.TaskType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FromStringtoTaskModel {
    public static TaskModel convert(String s){
        TaskModel taskModel = new TaskModel();
        List<String> findTaskTypeList= Arrays.stream(s.split("taskType")).collect(Collectors.toList());

        taskModel.setTaskType(TaskType.valueOf(findTaskTypeList.get(findTaskTypeList.size()-1)));
        String text = "";
        for (String k:findTaskTypeList) {
            text=text+k;
        }
        text=s.replaceFirst("TaskModeltaskText","");

        text = text.copyValueOf( text.substring(0,text.lastIndexOf("taskType")).toCharArray() );
        taskModel.setTaskText(text);
        return taskModel;
    }
}
