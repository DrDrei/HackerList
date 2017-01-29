package com.andreiusenka.hackerlist.taskinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andreiusenka.hackerlist.R;

public class TaskInfoActivity extends AppCompatActivity {

    public static final String TASK_ID_EXTRA = "TASK_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskinfo_activity);
    }


}
