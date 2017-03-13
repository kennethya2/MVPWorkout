package com.leafplain.excercis.mvpworkout.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView ;

import com.leafplain.excercis.mvpworkout.R;
import com.leafplain.excercis.mvpworkout.interfaces.LoginContract;
import com.leafplain.excercis.mvpworkout.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private Context mContext;
    private LoginContract.Presenter mPresenter;
    private String mUsername =   "";
    private String mPassword =   "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        String validateHint = " Validate \n User:"
                + mContext.getString(R.string.valid_user)
                + "\n Password:"
                + mContext.getString(R.string.valid_pass);

        ((TextView)findViewById(R.id.userPassTV)).setText(validateHint);

        mPresenter = new LoginPresenter(mContext, LoginActivity.this);

    }

    public void login(View v) {
        cleanAlert();
        mUsername = ((EditText)findViewById(R.id.usernameET)).getText().toString();
        mPassword = ((EditText)findViewById(R.id.passwordET)).getText().toString();

        mPresenter.login(mUsername, mPassword);
    }
    private void cleanAlert(){
        findViewById(R.id.alertTV).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.alertTV)).setText("");
    }

    @Override
    public void showProgress() {
        displayProgressBar(true);
    }

    @Override
    public void hideProgress() {
        displayProgressBar(false);
    }

    @Override
    public void onLoginSuccess() {
        try {
            Snackbar.make(findViewById(R.id.alertTV), "Login Success", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }catch (Exception e){}
    }

    @Override
    public void onLoginFail(String failMsg) {
        findViewById(R.id.alertTV).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.alertTV)).setText(failMsg);
    }

    private void displayProgressBar(boolean show){
        if(show){
            findViewById(R.id.loadingProgressBar).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.loadingProgressBar).setVisibility(View.GONE);;
        }
    }
}
