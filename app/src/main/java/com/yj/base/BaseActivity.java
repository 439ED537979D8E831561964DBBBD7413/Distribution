package com.yj.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by LK on 2017/8/31.
 * @author LK
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mcontext;
    Unbinder unbinder;
    protected LayoutInflater inflater;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder= ButterKnife.bind(this);
        mcontext = this;
        inflater=getLayoutInflater();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 获取布局文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();


}