package com.andreiusenka.hackerlist.Tasks;

import com.andreiusenka.hackerlist.BasePresenter;
import com.andreiusenka.hackerlist.BaseView;
import com.andreiusenka.hackerlist.data.Task;


public interface TaskContract {


    interface View extends BaseView<TaskContract.Presenter> {
        void updateData();
        void showTaskInfo(String taskID);
        void showAddNewTask();
    }

    interface Presenter extends BasePresenter {
        void taskClicked(Task task);
        void onPlayButtonToggle(Task task);
        void onCheckboxClicked(Task task);
        void addTaskClicked();
    }

}
