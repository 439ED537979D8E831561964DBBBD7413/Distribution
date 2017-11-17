package com.yj.loging.view;

/**
 * Created by LK on 2017/11/17.
 *
 * @author LK
 */

public interface LoginView {
    /**
     * 显示progress
     */
    void showProgress();

    /**
     * 隐藏progress
     */
    void hideProgress();

    /**
     *
     */
    void setUsernameError();

    /**
     *
     */
    void setPasswordError();

    /**
     *跳转到Home界面
     */
    void navigateToHome();
}
