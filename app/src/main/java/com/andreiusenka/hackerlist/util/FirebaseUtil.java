package com.andreiusenka.hackerlist.util;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by drei on 2017-01-28.
 */

public class FirebaseUtil {

    static public FirebaseAuth.AuthStateListener addLogOutListener(final Activity activity) {
        FirebaseAuth.AuthStateListener listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // Sign user out
                    Toasts.toastMessage(activity, "Logging out...");
                    LogUser.logUserOut(activity);
                }
            }
        };

        return listener;
    }

    static public FirebaseAuth.AuthStateListener addLogInListener(final Activity activity) {
        FirebaseAuth.AuthStateListener listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Sign user in
                    Toasts.toastMessage(activity, "Logging in...");
                    LogUser.logUserIn(activity);
                }
            }
        };

        return listener;
    }

//    static public void init()
}
