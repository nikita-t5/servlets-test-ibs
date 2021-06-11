package ru.ibs.taskServlets.model;

public class Task {
    String taskName;
    String deadline;

    public Task(String taskName, String deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
