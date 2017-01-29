package com.andreiusenka.hackerlist.taskinfo;

/**
 * Created by satyen on 28/01/17.
 */

public class TaskInfoPresenter implements TaskInfoContract.Presenter {

    private TaskInfoContract.View taskInfoView;

    @Override
    public void setTime(int hourOfDay, int minute) {

    }

    @Override
    public int getHourOfDay() {
        return 0;
    }

    @Override
    public int getMinute() {
        return 0;
    }

    public TaskInfoPresenter(TaskInfoContract.View taskInfoView) {
        taskInfoView.setPresenter(this);
        this.taskInfoView = taskInfoView;
    }


    @Override
    public void dateTextClicked() {
        taskInfoView.showDateDialogFragment();
    }

    @Override
    public void start() {

    }

    @Override
    public void setDate(int year, int month, int dayOfMonth) {

    }

    @Override
    public int getYear() {
        return 0;
    }

    @Override
    public int getMonth() {
        return 0;
    }

    @Override
    public int getDayOfMonth() {
        return 0;
    }
}
