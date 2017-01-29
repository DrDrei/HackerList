package com.andreiusenka.hackerlist.Tasks;

import android.support.annotation.NonNull;

import com.andreiusenka.hackerlist.data.Task;
import com.andreiusenka.hackerlist.login.LoginContract;


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
        taskView.showTaskInfo(task.getID());
    }

    @Override
    public void onPlayButtonToggle(Task task) {
        if (task.isActive()) {
            task.setInactive();
        } else {
            task.setActive();
        }

        taskView.updateData();
    }

    @Override
    public void onCheckboxClicked(Task task) {

    }
}
