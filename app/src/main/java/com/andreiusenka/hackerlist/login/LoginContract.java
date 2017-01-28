package com.andreiusenka.hackerlist.login;

import android.support.annotation.NonNull;

import com.andreiusenka.hackerlist.BasePresenter;
import com.andreiusenka.hackerlist.BaseView;

import java.util.List;

/**
 * Created by satyen on 28/01/17.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        public void loadLogInView();
    }

    interface Presenter extends BasePresenter {

        public void login();

    }


}
