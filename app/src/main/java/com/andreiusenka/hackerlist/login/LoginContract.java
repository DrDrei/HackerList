package com.andreiusenka.hackerlist.login;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.andreiusenka.hackerlist.BasePresenter;
import com.andreiusenka.hackerlist.BaseView;

import java.util.List;

/**
 * Created by satyen on 28/01/17.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void loadLogInView();
        void toastMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void login(EditText email, EditText password);

    }


}
