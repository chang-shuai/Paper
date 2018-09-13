package cn.com.bwing.paper.login.presenter;

import android.os.Handler;
import cn.com.bwing.paper.login.model.LoginModel;
import cn.com.bwing.paper.login.model.LoginModelImp;
import cn.com.bwing.paper.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginModel mLoginModel;
    private LoginView mLoginView;
    private Handler mHandler = new Handler();


    public LoginPresenterImpl(LoginView loginView) {
        mLoginView = loginView;
        mLoginModel = new LoginModelImp();
    }

    @Override
    public void validateIdentity(String username, String password) {
        mLoginModel.getLoginStatus(username, password, new LoginModel.LoginStatusListener() {

            @Override
            public void onSuccess() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.showToast("登录成功");
                    }
                });
            }

            @Override
            public void onFailure(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.showToast(msg);
                    }
                });
            }

        });
    }





}
