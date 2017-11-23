package com.yj.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yj.util.ShowLog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LK on 2017/8/30.
 *
 * @author LK
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    public static Toast mToast;
    Unbinder unbinder;

    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;

    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;

    /**
     * 获得全局的，防止使用getActivity()为空 * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mActivity).inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view, savedInstanceState);
        mIsPrepare = true;
        onLazyLoad();

    }

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view * @param view * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 执行数据的加载
     */
    protected abstract void initData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mIsVisible = false;
        mIsPrepare = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mIsVisible = true;
            onLazyLoad();
        } else {
            mIsVisible = false;
        }
    }

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     *
     * @author lk
     * @date 2016-5-26 下午4:10:20
     */
    protected void onLazyLoad() {
        if (mIsPrepare && mIsVisible) {
            initData();
            mIsVisible = false;
            mIsPrepare = false;

        }

    }
    protected void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
    protected void showLog(String msg) {
        ShowLog.e(msg);
    }
}
