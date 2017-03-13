package com.leafplain.excercis.mvpworkout.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.leafplain.excercis.mvpworkout.R;
import com.leafplain.excercis.mvpworkout.interfaces.LoginContract;
import com.leafplain.excercis.mvpworkout.model.User;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kennethyeh on 2017/3/13.
 */

public class LoginPresenter implements LoginContract.Presenter{

    private Context mContext;
    private LoginContract.View mLoginView;

    private User mUser;
    private Handler mLoginHandler = new Handler();

    public LoginPresenter(@NonNull Context context, @NonNull LoginContract.View loginView) {
        mContext    = checkNotNull(context, "context cannot be null!");
        mLoginView  = checkNotNull(loginView, "loginView cannot be null!");
        mUser       = new User();
    }

    @Override
    public void login(String username, String password) {
        mLoginView.showProgress();
        mUser.username = username;
        mUser.password = password;
        if (TextUtils.isEmpty(username.trim())){
            mLoginView.onLoginFail("need username");
            mLoginView.hideProgress();
            return;
        }
        if (TextUtils.isEmpty(password.trim())){
            mLoginView.onLoginFail("need password");
            mLoginView.hideProgress();
            return;
        }
        mLoginHandler.postDelayed(mLoginRunnable, 2000);
    }

    private Runnable mLoginRunnable = new Runnable() {
        @Override
        public void run() {
            if (!mUser.username.trim().equals(mContext.getString(R.string.valid_user))){
                mLoginView.onLoginFail("invalidate user");
                mLoginView.hideProgress();
                return;
            }

            if (!mUser.password.trim().equals(mContext.getString(R.string.valid_pass))){
                mLoginView.onLoginFail("invalidate password");
                mLoginView.hideProgress();
                return;
            }
            mLoginView.onLoginSuccess();
            mLoginView.hideProgress();
        }
    };

}
