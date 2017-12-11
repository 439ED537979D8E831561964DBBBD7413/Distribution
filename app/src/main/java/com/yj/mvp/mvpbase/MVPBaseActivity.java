package com.yj.mvp.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yj.base.BaseActivity;

/**
 * Created by LK on 2017/12/8.
 *
 * @author LK
 */

public abstract class MVPBaseActivity<V, P extends BasePresenter<V>> extends BaseActivity {
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();//创建Presenter
        mPresenter.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract P createPresenter();

}