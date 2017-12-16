package com.yj.mvp.mvpbase;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.yj.application.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by LK on 2017/8/31.
 *
 * @author LK
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mcontext;
    Unbinder unbinder;
    protected LayoutInflater inflater;
    public static Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mcontext = this;
        inflater = getLayoutInflater();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 获取布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mcontext, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    protected void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
