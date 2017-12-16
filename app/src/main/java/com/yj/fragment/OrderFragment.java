package com.yj.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yj.adpter.OrderVpAdapter;
import com.yj.mvp.mvpbase.BaseFragment;
import com.yj.distribution.R;
import com.yj.other.ParentViewPager;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author LK
 */
public class OrderFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ParentViewPager viewPager;
    private OrderVpAdapter adapterVP;

    private int[] imags = {R.drawable.audit, R.drawable.distribution, R.drawable.sucdistribution};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ordel;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        adapterVP = new OrderVpAdapter(getFragmentManager(), mActivity);

    }

    @Override
    protected void initData() {
        viewPager.setAdapter(adapterVP);
        tablayout.setupWithViewPager(viewPager);
        setTabUpView();
    }

    private void setTabUpView() {
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            if (tab != null) {
                tab.setIcon(imags[i]);
            }
        }
    }

}
