package com.andreiusenka.hackerlist.login;

import android.support.v4.app.Fragment;

/**
 * Created by satyen on 28/01/17.
 */

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mLoginPresenter;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mLoginPresenter = presenter;
    }
}
