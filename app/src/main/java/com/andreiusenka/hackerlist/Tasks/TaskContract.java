package com.andreiusenka.hackerlist.Tasks;

import com.andreiusenka.hackerlist.BasePresenter;
import com.andreiusenka.hackerlist.BaseView;


public interface TaskContract {


    interface View extends BaseView<TaskContract.Presenter> {

    }

    interface Presenter extends BasePresenter {

    }

}
