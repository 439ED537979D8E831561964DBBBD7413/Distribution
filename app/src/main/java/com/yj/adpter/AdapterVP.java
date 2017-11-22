package com.yj.adpter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yj.distribution.R;


/**
 * Created by Administrator on 2017/7/22 0022.
 *
 * @author LK
 */

public class AdapterVP extends FragmentPagerAdapter {
    private Fragment[] fragmentList;
    private Context mContext;
    public AdapterVP(FragmentManager fm, Fragment[] fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }
    public AdapterVP(FragmentManager fm, Fragment[] fragmentList, Context context) {
        super(fm);
        this.fragmentList = fragmentList;
        this.mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList[position];
    }

    @Override
    public int getCount() {
        return fragmentList.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getStringArray(R.array.fragment_name)[position];
    }
}
