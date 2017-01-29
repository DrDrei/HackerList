package com.andreiusenka.hackerlist.taskinfo;

/**
 * Created by satyen on 28/01/17.
 */

public class TaskInfoPresenter implements TaskInfoContract.Presenter {

    private TaskInfoContract.View taskInfoView;

    public TaskInfoPresenter(TaskInfoContract.View taskInfoView) {
        this.taskInfoView = taskInfoView;
    }


    @Override
    public void dateTextClicked() {
        taskInfoView.showDateDialogFragment();
    }

    @Override
    public void start() {

    }
}
