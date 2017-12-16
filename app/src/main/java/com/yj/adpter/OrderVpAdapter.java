package com.yj.adpter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yj.common.Constant;
import com.yj.distribution.R;
import com.yj.fragment.FragmentFactory;


/**
 * Created by Administrator on 2017/7/22 0022.
 *
 * @author LK
 */

public class OrderVpAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public OrderVpAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createForOrder(position);
    }

    @Override
    public int getCount() {
        return Constant.ORDERTYPE.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getStringArray(R.array.fragment_name)[position];
    }
}
