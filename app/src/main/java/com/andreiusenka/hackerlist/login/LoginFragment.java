package com.andreiusenka.hackerlist.login;

import android.content.Intent;
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
import android.widget.Toast;

import com.andreiusenka.hackerlist.R;
import com.andreiusenka.hackerlist.Tasks.TaskActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment implements LoginContract.View {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

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

        // Set up firebase


        // Set Up Button and EditText fields
        loginButton = (Button) root.findViewById(R.id.button_login);
        emailEditText = (EditText) root.findViewById(R.id.login_acitivity_email);
        passwordEditText = (EditText) root.findViewById(R.id.login_activity_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.login(emailEditText, passwordEditText);
            }
        });

        // TODO: 2017-01-28 finish adding the toolbar log out
//        if (id == R.id.action_logout) {
//            return true;
//        }

        return root;
    }

    public void loadLogInView() {
        Intent intent = new Intent(getContext(), TaskActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void toastMessage(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
