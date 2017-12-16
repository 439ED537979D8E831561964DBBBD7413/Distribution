package com.yj.mvp.loging;

import android.content.Context;

/**
 * Created by LK on 2017/12/9.
 *
 * @author LK
 */

public class LoginContract {
    public interface Model {
        void login(String username, String password, Context context);

        void onDestroys();
    }

    public interface View<T> {
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
         *
         * @param msg
         */
        void setError(String msg);

        /**
         * 跳转到Home界面
         */
        void navigateToHome(T t);
    }

    public interface Presenter {
        void validateCredentials(String username, String password);

        void onDestroy();
    }

    public interface OnLoginFinishedListener<T> {
        void onError(String msg);

        void onSuccess(T t);
    }

}
