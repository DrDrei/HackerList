package com.andreiusenka.hackerlist.Tasks;

import android.support.annotation.NonNull;

import com.andreiusenka.hackerlist.data.Task;


public class TaskPresenter implements TaskContract.Presenter  {

    @Override
    public void start() {

    }

    TaskPresenter (@NonNull TaskContract.View taskView) {
        taskView.setPresenter(this);
    }

    public void taskClicked(Task task) {

    }
}
