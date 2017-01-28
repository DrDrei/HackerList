package com.andreiusenka.hackerlist.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewGroupCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andreiusenka.hackerlist.R;

/**
 * Created by satyen on 28/01/17.
 */

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mLoginPresenter;


    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mLoginPresenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment, container, false);

        // Set Up Button
        loginButton = (Button) root.findViewById(R.id.button_login);

        return root;
    }
}
