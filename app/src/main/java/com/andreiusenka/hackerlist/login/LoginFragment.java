package com.andreiusenka.hackerlist.login;

import android.app.Activity;
import android.content.Intent;
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
import com.andreiusenka.hackerlist.Tasks.TaskActivity;
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    toastMessage("Logging user in...");
                    logUserIn();
                } else {
                    toastMessage("Logging user out...");
                    // User is signed out
//                    logUserOut();
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

        // TODO: 2017-01-28 finish adding the toolbar log out
//        if (id == R.id.action_logout) {
//            return true;
//        }

        return root;
    }


    public void firebaseLogin(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("drei3000@gmail.com", "rewind").addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    logUserIn();
                    toastMessage("Logging in...");
                } else {
                    Log.i("FireBase", "signInWithEmail", task.getException());
                    Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void logUserIn() {
        Intent intent = new Intent(getContext(), TaskActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

//    public void logUserOut() {
//        Intent intent = new Intent(getContext(), LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }

    public void toastMessage(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
