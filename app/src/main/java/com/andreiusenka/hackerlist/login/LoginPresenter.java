package com.andreiusenka.hackerlist.login;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by satyen on 28/01/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    private FirebaseAuth mFirebaseAuth;

    @Override
    public void start() {

    }

    LoginPresenter (@NonNull LoginContract.View loginView) {
        loginView.setPresenter(this);
        this.loginView = loginView;
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    public void login(EditText email, EditText password) {
        String emailText = email.getText().toString().trim();
        String passwordText = email.getText().toString().trim();


        if (emailText.isEmpty() || passwordText.isEmpty()) {
            loginView.toastMessage("Please fill in all fields.");
        } else {
            loginView.firebaseLogin(mFirebaseAuth, emailText, passwordText);
        }


    }


}
