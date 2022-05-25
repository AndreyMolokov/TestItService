package ru.testitservice.model;

import ru.testitservice.entity.TaskData;
import ru.testitservice.tasktype.TaskType;

public class TaskModel {

    TaskType taskType;

    String taskText;

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }



    @Override
    public String toString() {
        return "TaskModel"+"taskText"+taskText +"taskType"+taskType;
    }


}
