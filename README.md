

###簡易登入流程 實作Android MVP架構
----

#### MVP

 <img src="https://raw.githubusercontent.com/kennethya2/MVPWorkout/master/markdown/mvp.png" width="350">

將動作邏輯從畫面View抽離，只負責輸入與資料呈現，簡化UI層(Activity,Fragment)程式碼。
動作邏輯以Presenter介面實作，透過Presenter層對Model資料將行存取，並將結果還傳給UI層作顯示。
 

#### Interface

Contract:
定義畫面(View)與動作(Presenter)相互對應interface

<pre><code> 
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
</code></pre>

View :
定義一般Activity or Fragment 畫面顯示所需實作介面

- showProgress() : 登入中 顯示progress
- hideProgress() : 登入結束 停止progress
- onLoginSuccess(): 登入成功
- onLoginFail(String failMsg):登入失敗訊息


Presenter:
定義動作行為或後端資料存取所需實作介面

- login(String username, String password):View呼叫login動作進行登入




#### LoginActivity

- View click login
- 
View向Presenter請求登入 mPresenter.login(mUsername, mPassword)

<pre><code> 
    public void login(View v) {
        ...

        mPresenter.login(mUsername, mPassword);
    }
</code></pre>



#### LoginPresenter

- 進行登入

 呼叫畫面顯示progress  mLoginView.showProgress()

<img src="https://raw.githubusercontent.com/kennethya2/MVPWorkout/master/markdown/login_progress.png" width="220">

- 登入錯誤 

呼叫畫面顯示異常 mLoginView.onLoginFail("need username")


<img src="https://raw.githubusercontent.com/kennethya2/MVPWorkout/master/markdown/login_fail.png" width="220">


<pre><code> 
@Override
    public void login(String username, String password) {
        mLoginView.showProgress();
        ...
        if (TextUtils.isEmpty(username.trim())){
            mLoginView.onLoginFail("need username");
            mLoginView.hideProgress();
            return;
        }
        ...
        mLoginHandler.postDelayed(mLoginRunnable, 2000);
    }
</code></pre>

- 完成登入

呼叫畫面顯示成功訊息 mLoginView.onLoginSuccess()
呼叫畫面停止progress mLoginView.hideProgress()


<img src="https://raw.githubusercontent.com/kennethya2/MVPWorkout/master/markdown/login_done.png" width="220">

<pre><code> 
private Runnable mLoginRunnable = new Runnable() {
        @Override
        public void run() {
            ...
            mLoginView.onLoginSuccess();
            mLoginView.hideProgress();
        }
    };
</code></pre>

