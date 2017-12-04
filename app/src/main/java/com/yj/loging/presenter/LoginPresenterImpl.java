package com.yj.loging.presenter;

import android.content.Context;
import android.os.Bundle;

import com.yj.loging.OnLoginFinishedListener;
import com.yj.loging.model.LoginModel;
import com.yj.loging.model.LoginModelImpl;
import com.yj.loging.view.LoginView;

/**
 * Created by Anthony on 2016/2/15.
 * Class Note:
 * 1 完成presenter的实现。这里面主要是Model层和View层的交互和操作。
 * 2  presenter里面还有个OnLoginFinishedListener，
 * 其在Presenter层实现，给Model层回调，更改View层的状态，
 * 确保 Model层不直接操作View层。如果没有这一接口在LoginPresenterImpl实现的话，
 * LoginPresenterImpl只 有View和Model的引用那么Model怎么把结果告诉View呢？
 *
 * @author LK
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {
    private LoginView loginView;
    private LoginModel loginModel;
    private Context mContext;
    public LoginPresenterImpl(LoginView loginView, Context context) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
        this.mContext=context;
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }
        loginModel.login(username, password, this,mContext);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        loginModel.onDestroys();
    }

    @Override
    public void onError(String msg) {
        if (loginView != null) {
            loginView.setError(msg);
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess(Bundle bundle) {
        if (loginView != null) {
            loginView.navigateToHome(bundle);
        }
    }
}
