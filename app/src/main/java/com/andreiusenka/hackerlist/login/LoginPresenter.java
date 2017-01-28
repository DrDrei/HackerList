package com.andreiusenka.hackerlist.login;

import android.support.annotation.NonNull;

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

    public void login() {
        loginView.loadLogInView();
    }
}
