package com.yj.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yj.base.BaseFragment;
import com.yj.distribution.R;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author LK
 */
public class OrderContentFragment extends BaseFragment {

    public static OrderContentFragment newInstance(int type) {
        return newInstance(type, false);
    }

    public static OrderContentFragment newInstance(int type, boolean isFirstShow) {

        Bundle args = new Bundle();
        OrderContentFragment fragment = new OrderContentFragment();
        args.putInt("type", type);
        args.putBoolean("FirstShow", isFirstShow);
        fragment.setArguments(args);
        return fragment;
    }
    public int getType() {
        return getArguments().getInt("type");
    }

    public boolean isFirstShown() {
        return getArguments().getBoolean("isFirstShow");
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ordel_content;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

}
