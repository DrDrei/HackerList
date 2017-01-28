package com.andreiusenka.hackerlist.Tasks;

import android.support.v4.app.Fragment;


public class TaskFragment extends Fragment implements TaskContract.View  {

    private TaskContract.Presenter mTaskPresenter;

    public static TaskFragment newInstance() {
        return new TaskFragment();
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        mTaskPresenter = presenter;
    }
}
