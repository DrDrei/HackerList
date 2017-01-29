package com.andreiusenka.hackerlist.taskinfo;

import com.andreiusenka.hackerlist.BasePresenter;
import com.andreiusenka.hackerlist.BaseView;
import com.andreiusenka.hackerlist.entities.TimeSegment;

import java.util.List;

/**
 * Created by satyen on 28/01/17.
 */

public interface TaskInfoContract {

    interface View extends BaseView<Presenter> {
        void showDateDialogFragment();
        void updateData();
        void setData(List<TimeSegment> times);

        void setCompletionCheckbox(boolean value);
        void setDurationText(String duration);
        void setDateText(String date);
        void setTitleText(String title);
    }

    interface Presenter extends BasePresenter{
        void saveTask();
        void deleteTask();

        void dateTextClicked();
        void setDate(int year, int month, int dayOfMonth);
        int getYear();
        int getMonth();
        int getDayOfMonth();
        void setTime(int hourOfDay, int minute);
        int getHourOfDay();
        int getMinute();
    }

}
