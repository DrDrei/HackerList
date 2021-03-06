package com.andreiusenka.hackerlist.util;

import android.util.Log;

import com.andreiusenka.hackerlist.entities.Task;
import com.andreiusenka.hackerlist.entities.User;
import com.andreiusenka.hackerlist.entities.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FirebaseInterface {
    private static String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public static String getUserUid() {
        return uid;
    }

    public FirebaseInterface() {}

    static public void registerUser(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(Utils.FIREBASE_USERS  + "/" + uid);
        try {
            ref.setValue(user);
        } catch (Exception e) {
            Log.i("FB", "something went wrong", e);
        }
    }

    static public String registerTaskId() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(Utils.FIREBASE_TASKS);
        return ref.push().getKey();
    }

    static public void registerTask(Task task) {
        try {
            getUserTaskRef(task).setValue(task);
        } catch (Exception e) {
            Log.i("FB", "something went wrong", e);
        }
    }

    static public void removeTask(Task task) {
        try {
            getUserTasksRef().child(task.getTaskID()).removeValue();
        } catch (Exception e) {
            Log.i("FB", "something went wrong", e);
        }
    }

    static public DatabaseReference getUserTaskRef(Task task) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(Utils.FIREBASE_USERS + "/" + uid + "/" + Utils.FIREBASE_TASKS + "/" + task.getTaskID());
        return ref;
    }


    static public DatabaseReference getUserTasksRef() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(Utils.FIREBASE_USERS + "/" + uid + "/" + Utils.FIREBASE_TASKS);
        return ref;
    }
}
