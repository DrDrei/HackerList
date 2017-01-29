package com.andreiusenka.hackerlist.taskinfo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.andreiusenka.hackerlist.entities.Task;
import com.andreiusenka.hackerlist.entities.TaskSingleton;
import com.andreiusenka.hackerlist.util.Toasts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by satyen on 28/01/17.
 */

public class TaskInfoPresenter implements TaskInfoContract.Presenter {

    private Task task;
    private TaskInfoContract.View taskInfoView;
    private Context context;

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

    public TaskInfoPresenter(TaskInfoContract.View taskInfoView, String taskID, Context context) {
        taskInfoView.setPresenter(this);
        this.taskInfoView = taskInfoView;
        task = TaskSingleton.getInstance().getTask();
        this.context = context;
    }


    @Override
    public void dateTextClicked() {
        taskInfoView.showDateDialogFragment();
    }

    @Override
    public void start() {
        taskInfoView.setCompletionCheckbox(task.getCompleted());
        taskInfoView.setDateText(task.getScheduledDateText());
        taskInfoView.setDurationText(task.getDurationString());
        taskInfoView.setTitleText(task.getTitle());
        taskInfoView.setData(task.getTimeSegList());
    }

    @Override
    public void setDate(int year, int month, int dayOfMonth) {
        task.setScheduledDate(new GregorianCalendar(year, month, dayOfMonth).getTimeInMillis());
        taskInfoView.setDateText(task.getScheduledDateText());
    }

    @Override
    public int getYear() {
        Date date = new Date(task.getScheduledDate());
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    @Override
    public int getMonth() {
        Date date = new Date(task.getScheduledDate());
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    @Override
    public int getDayOfMonth() {
        Date date = new Date(task.getScheduledDate());
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void saveTask() {

        task.setTitle(taskInfoView.getTitleText());
        task.setCompleted(taskInfoView.getCompletionCheckbox());

        task.updateTask();
        Toasts.toastMessage(context, "Saved Task.");

        taskInfoView.returnToPrevious();
    }

    @Override
    public void deleteTask() {
        taskInfoView.showDeleteAlert();
    }
}
