package com.andreiusenka.hackerlist.taskinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.andreiusenka.hackerlist.R;
import com.andreiusenka.hackerlist.util.ActivityUtils;

public class TaskInfoActivity extends AppCompatActivity {

    public static final String TASK_ID_EXTRA = "TASK_ID";

    public static final int REQUEST_ADD_TASK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskinfo_activity);


        TaskInfoFragment taskInfoFragment = (TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.contentTaskInfo);
        if (taskInfoFragment == null) {
            taskInfoFragment = taskInfoFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), taskInfoFragment, R.id.contentTaskInfo);
        }

        // Create the presenter
        TaskInfoPresenter taskPresenter = new TaskInfoPresenter(taskInfoFragment);
    }


}
