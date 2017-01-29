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
import com.andreiusenka.hackerlist.entities.User;
import com.andreiusenka.hackerlist.util.FirebaseInterface;
import com.andreiusenka.hackerlist.util.Toasts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import static com.andreiusenka.hackerlist.util.FirebaseUtil.addLogInListener;


public class LoginFragment extends Fragment implements LoginContract.View {

    private FirebaseAuth.AuthStateListener loginAuthListener;
    private FirebaseAuth.AuthStateListener registerAuthListener;

    private LoginContract.Presenter mLoginPresenter;

    private Button loginButton;
    private Button registrationButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(loginAuthListener);
        FirebaseAuth.getInstance().addAuthStateListener(registerAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(loginAuthListener);
        FirebaseAuth.getInstance().removeAuthStateListener(registerAuthListener);
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

        loginAuthListener = addLogInListener(getActivity());
        registerAuthListener = addLogInListener(getActivity());

    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment, container, false);

        // Set Up Button and EditText fields
        registrationButton = (Button) root.findViewById(R.id.button_register);
        loginButton = (Button) root.findViewById(R.id.button_login);
        emailEditText = (EditText) root.findViewById(R.id.login_acitivity_email);
        passwordEditText = (EditText) root.findViewById(R.id.login_activity_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.login(emailEditText, passwordEditText);
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.register(emailEditText, passwordEditText);
            }
        });

        return root;
    }


    public void firebaseLogin(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
//        FirebaseAuth.getInstance().signInWithEmailAndPassword("drei3000@gmail.com", "rewind").addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.i("FireBase", "signing in with email", task.getException());
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        Toasts.toastMessage(getContext(), "Invalid email/pass combination.");
                    } catch (FirebaseAuthInvalidUserException e) {
                        Toasts.toastMessage(getContext(), "Email not found.");
                    } catch (Exception e) {
                        Toasts.toastMessage(getContext(), "Authentication failed. Check connection.");
                    }
                }
            }
        });
    }

    @Override
    public void firebaseRegister(final String emailText, String passwordText) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.i("FireBaseException", "registering with email", task.getException());
                    // TODO: 2017-01-28 fix the firebase exception problem...
                    try {
                        throw (FirebaseAuthException) task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        Toasts.toastMessage(getContext(), "Weak password, enter 6+ characters.");
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        Toasts.toastMessage(getContext(), "Invalid email/pass combination.");
                    } catch (FirebaseAuthInvalidUserException e) {
                        Toasts.toastMessage(getContext(), "Email not found.");
                    } catch (Exception e) {
                        Toasts.toastMessage(getContext(), "Something is wrong...");
                    }
                } else {
                    // init the user on firebase here...
                    User user = new User(emailText);
                    FirebaseInterface.registerUser(user);
                }
            }
        });
    }


    public void toastMessage(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
