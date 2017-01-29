package com.andreiusenka.hackerlist.Tasks;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;

import com.andreiusenka.hackerlist.entities.Task;
import com.andreiusenka.hackerlist.entities.TaskSingleton;


public class TaskPresenter implements TaskContract.Presenter  {
    private TaskContract.View taskView;

    @Override
    public void start() {
    }


    TaskPresenter (@NonNull TaskContract.View taskView) {
        taskView.setPresenter(this);
        this.taskView = taskView;
    }

    public void taskClicked(Task task) {
        TaskSingleton.getInstance().setTask(task);
        taskView.showTaskInfo(task.getTaskID());
    }

    @Override
    public void onPlayButtonToggle(final Task task) {
        if (task.isActive()) {
            task.setActive(false);
            task.stopSegment();
        } else {
            task.setActive(true);
            task.startSegment();
        }
        task.updateTask();
    }

    @Override
    public void onCheckboxClicked(Task task) {
        if (task.getCompleted()) {
            task.setCompleted(false);
        } else {
            task.setCompleted(true);
        }
        task.updateTask();
    }

    @Override
    public void addTaskClicked(Task task) {
        // TODO: add new task with firebase and such
        TaskSingleton.getInstance().setTask(task);
        taskView.showAddNewTask();
    }

    
}
