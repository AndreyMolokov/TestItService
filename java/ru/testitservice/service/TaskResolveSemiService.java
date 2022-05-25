package ru.testitservice.service;

import ru.testitservice.tasktype.TaskType;

public interface TaskResolveSemiService {
    public String processing(String userEnter);
    public String validate(String userString) ;
    public TaskType getTaskType();
}
