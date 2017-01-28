package com.andreiusenka.hackerlist.Tasks;

import android.support.annotation.NonNull;



public class TaskPresenter implements TaskContract.Presenter  {

    @Override
    public void start() {

    }

    TaskPresenter (@NonNull TaskContract.View taskView) {
        taskView.setPresenter(this);
    }
}
