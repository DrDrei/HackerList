package com.andreiusenka.hackerlist.taskinfo;

import com.andreiusenka.hackerlist.BasePresenter;
import com.andreiusenka.hackerlist.BaseView;

/**
 * Created by satyen on 28/01/17.
 */

public interface TaskInfoContract {

    interface View extends BaseView<Presenter> {
        void showDateDialogFragment();
    }

    interface Presenter extends BasePresenter{
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
