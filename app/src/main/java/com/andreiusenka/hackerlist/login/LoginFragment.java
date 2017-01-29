package com.andreiusenka.hackerlist.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andreiusenka.hackerlist.R;
import com.andreiusenka.hackerlist.util.LogUser;
import com.andreiusenka.hackerlist.util.Toasts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment implements LoginContract.View {

    private FirebaseAuth.AuthStateListener mAuthListener;

    private LoginContract.Presenter mLoginPresenter;

    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
    }

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

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Sign user in
                    Toasts.toastMessage(getContext(), "Logging in...");
                    LogUser.logUserIn(getActivity());
                }
            }
        };

    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment, container, false);

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

        return root;
    }


    public void firebaseLogin(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("drei3000@gmail.com", "rewind").addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.i("FireBase", "signInWithEmail", task.getException());
                    Toasts.toastMessage(getContext(), "Authentication failed.");
                }
            }
        });
    }

    public void toastMessage(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
