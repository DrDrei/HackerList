package com.andreiusenka.hackerlist.taskinfo;

import android.support.v4.app.Fragment;

/**
 * Created by satyen on 28/01/17.
 */

public class TaskInfoFragment extends Fragment implements TaskInfoContract.View {
    TaskInfoContract.Presenter mTaskInfoPresenter;

    


    @Override
    public void setPresenter(TaskInfoContract.Presenter presenter) {
        mTaskInfoPresenter = presenter;
    }


}
