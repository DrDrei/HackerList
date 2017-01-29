package com.andreiusenka.hackerlist.login;

import android.support.annotation.NonNull;
import android.widget.EditText;


public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;

    @Override
    public void start() {
    }

    LoginPresenter (@NonNull LoginContract.View loginView) {
        loginView.setPresenter(this);
        this.loginView = loginView;
    }

    public void login(EditText email, EditText password) {
        String emailText = email.getText().toString().trim();
        String passwordText = email.getText().toString().trim();


        if (emailText.isEmpty() || passwordText.isEmpty()) {
            loginView.toastMessage("Please fill in all fields.");
        } else {
            loginView.firebaseLogin(emailText, passwordText);
        }


    }


}
