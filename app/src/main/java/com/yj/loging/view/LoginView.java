package com.yj.loging.view;

import android.os.Bundle;

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
     * 设置错误信息
     * @param msg
     */
    void setError(String msg);

    /**
     *跳转到Home界面
     */
    void navigateToHome(Bundle bundle);
}
