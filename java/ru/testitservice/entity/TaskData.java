package ru.testitservice.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.testitservice.model.TaskModel;
import ru.testitservice.tasktype.TaskType;

import javax.persistence.*;

@Entity
@Table(name = "\"Task\"")


public class TaskData {

    @Column(name = "TASK_TEXT",nullable = false)
    String taskText;

    @Enumerated(EnumType.STRING)
    @Column(name = "TASK_TYPE",nullable = false)
    TaskType taskType;

    @Column(name = "TASK_ID",nullable = false)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long taskDataId;

    @Column(name = "TASK_DATE",nullable = false)
    String date;

    public TaskModel toTaskModel( ){
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskText(taskText);
        taskModel.setTaskType(taskType);
        return taskModel;
    }

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

    public Long getTaskDataId() {
        return taskDataId;
    }

    public void setTaskDataId(Long taskDataId) {
        this.taskDataId = taskDataId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
