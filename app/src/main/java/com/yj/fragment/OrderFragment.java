package com.yj.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yj.adpter.AdapterVP;
import com.yj.base.BaseFragment;
import com.yj.common.Constant;
import com.yj.distribution.R;
import com.yj.other.ParentViewPager;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author LK
 */
public class OrderFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ParentViewPager viewPager;
    AdapterVP adapterVP;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ordel;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Fragment[] fragments={OrderContentFragment.newInstance(Constant.TYPE_AUDIT),OrderContentFragment.newInstance(Constant.TYPE_DISTRIBTNION),OrderContentFragment.newInstance(Constant.TYPE_SUCCEED)};
        adapterVP=new AdapterVP(getFragmentManager(),fragments);
        viewPager.setAdapter(adapterVP);
        tablayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
