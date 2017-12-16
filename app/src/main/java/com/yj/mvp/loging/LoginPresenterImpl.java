package com.yj.mvp.loging;

import android.content.Context;

import com.yj.bean.User;
import com.yj.mvp.mvpbase.BasePresenter;
import com.yj.mvp.loging.LoginContract;
import com.yj.mvp.loging.LoginModelImpl;

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
public class LoginPresenterImpl extends BasePresenter<LoginContract.View<User>>implements LoginContract.Presenter, LoginContract.OnLoginFinishedListener<User> {
    private LoginContract.View<User> loginView;
    private LoginModelImpl loginModel;
    private Context mContext;

    public LoginPresenterImpl(LoginContract.View<User> loginView, Context context) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl(this);
        this.mContext = context;
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }
        loginModel.login(username, password, mContext);
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
    public void onSuccess(User user) {
        if (loginView != null) {
            loginView.navigateToHome(user);
        }
    }


    @Override
    public void start() {

    }
}
