package com.leafplain.excercis.mvpworkout.interfaces;

/**
 * Created by kennethyeh on 2017/3/13.
 */

public interface LoginContract {

    interface View {

        void showProgress();

        void hideProgress();

        void onLoginSuccess();

        void onLoginFail(String failMsg);

    }

    interface Presenter {

        void login(String username, String password);

    }
}
