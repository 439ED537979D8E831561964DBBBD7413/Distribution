package com.yj.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yj.adpter.AdapterVP;
import com.yj.base.BaseFragment;
import com.yj.common.Constant;
import com.yj.distribution.R;
import com.yj.mvp.order.view.OrderContentFragment;
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
    AdapterVP adapterVP;
    private int[] imags = {R.drawable.audit,R.drawable.distribution,R.drawable.sucdistribution};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ordel;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Fragment[] fragments = {OrderContentFragment.newInstance(Constant.TYPE_AUDIT, true), OrderContentFragment.newInstance(Constant.TYPE_DISTRIBTNION), OrderContentFragment.newInstance(Constant.TYPE_SUCCEED)};
        adapterVP = new AdapterVP(getFragmentManager(), fragments, mActivity);
        viewPager.setAdapter(adapterVP);
        tablayout.setupWithViewPager(viewPager);
        setTabUpView();
    }

    @Override
    protected void initData() {

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
