package com.andreiusenka.hackerlist.login;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;

import com.andreiusenka.hackerlist.util.Toasting;

/**
 * Created by satyen on 28/01/17.
 */

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

        if (emailText.isEmpty()) {
            loginView.toastMessage("Please enter an email.");
        } else {
            loginView.loadLogInView();
        }


    }


}
