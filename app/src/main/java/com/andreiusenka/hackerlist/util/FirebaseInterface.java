package com.andreiusenka.hackerlist.util;

import android.util.Log;

import com.andreiusenka.hackerlist.entities.User;
import com.andreiusenka.hackerlist.entities.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FirebaseInterface {
    private static String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

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
}
