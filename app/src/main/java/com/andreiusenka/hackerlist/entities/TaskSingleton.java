package com.andreiusenka.hackerlist.entities;

/**
 * Created by drei on 2017-01-29.
 */
public class TaskSingleton {

    private Task tempTask;

    private static TaskSingleton ourInstance = new TaskSingleton();

    public static TaskSingleton getInstance() {
        return ourInstance;
    }

    private TaskSingleton() {}

    public void setTask(Task task) {
        tempTask = task;
    }

    public Task getTask() {
        return this.tempTask;
    }
}
