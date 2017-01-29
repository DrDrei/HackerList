package com.andreiusenka.hackerlist.Tasks;

import android.support.annotation.NonNull;

import com.andreiusenka.hackerlist.entities.Task;
import com.andreiusenka.hackerlist.util.FirebaseInterface;

import java.util.ArrayList;


public class TaskPresenter implements TaskContract.Presenter  {

    private TaskContract.View taskView;
//    private ArrayList<Task> taskList = new ArrayList<>();

    @Override
    public void start() {
//        loadTasks();
    }

//    private void loadTasks() {
//        Task task = new Task("some task");
//        taskList.add(task);
//        FirebaseInterface.pullUserTasks();
//        taskView.updateData(taskList);
//    }

    TaskPresenter (@NonNull TaskContract.View taskView) {
        taskView.setPresenter(this);
        this.taskView = taskView;
    }

    public void taskClicked(Task task) {
        taskView.showTaskInfo(task.getTaskID());
    }

    @Override
    public void onPlayButtonToggle(Task task) {
        if (task.isActive()) {
            task.setActive(false);
        } else {
            task.setActive(true);
        }
        task.updateTask();
//        taskView.updateData(taskList);
    }

    @Override
    public void onCheckboxClicked(Task task) {
        if (task.getCompleted()) {
            task.setCompleted(false);
        } else {
            task.setCompleted(true);
        }
        task.updateTask();
//        taskView.updateData(taskList);
    }

    @Override
    public void addTaskClicked() {
        // TODO: add new task with firebase and such
//        taskView.showAddNewTask();
    }
}
