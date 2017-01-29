package com.andreiusenka.hackerlist.taskinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.andreiusenka.hackerlist.R;
import com.andreiusenka.hackerlist.util.ActivityUtils;

public class TaskInfoActivity extends AppCompatActivity {

    public static final String TASK_ID_EXTRA = "TASK_ID";

    public static final int REQUEST_ADD_TASK = 1;

    private TaskInfoContract.Presenter taskInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskinfo_activity);

        String taskID = getIntent().getStringExtra(TASK_ID_EXTRA);

        TaskInfoFragment taskInfoFragment = (TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.contentTaskInfo);
        if (taskInfoFragment == null) {
            taskInfoFragment = taskInfoFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), taskInfoFragment, R.id.contentTaskInfo);
        }

        // Create the presenter
        taskInfoPresenter = new TaskInfoPresenter(taskInfoFragment, taskID, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskinfo_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.taskinfo_activity_save:
                taskInfoPresenter.saveTask();
                return true;
            case R.id.taskinfo_activity_delete:
                taskInfoPresenter.deleteTask();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
