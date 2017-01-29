package com.andreiusenka.hackerlist.util;

import android.app.Activity;
import android.content.Intent;

import com.andreiusenka.hackerlist.Tasks.TaskActivity;
import com.andreiusenka.hackerlist.login.LoginActivity;


public class LogUser {
    static public void logUserIn(Activity activity) {
        Intent intent = new Intent(activity, TaskActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    static public void logUserOut(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }
}
