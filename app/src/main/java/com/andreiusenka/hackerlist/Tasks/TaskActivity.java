package com.andreiusenka.hackerlist.Tasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.andreiusenka.hackerlist.R;
import com.andreiusenka.hackerlist.util.ActivityUtils;



public class TaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);

        // Initialize toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize Navigation drawer

        //Create the view
        TaskFragment taskFragment = (TaskFragment) getSupportFragmentManager().findFragmentById(R.id.contentTask);
        if (taskFragment == null) {
            taskFragment = taskFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), taskFragment, R.id.contentTask);
        }

        // Create the presenter
        TaskPresenter taskPresenter = new TaskPresenter(taskFragment);

    }
}
