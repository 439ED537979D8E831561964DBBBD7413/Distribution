package com.yj.adpter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yj.fragment.FragmentFactory;


/**
 * Created by Administrator on 2017/7/22 0022.
 *
 * @author LK
 */

public class AdapterVP extends FragmentPagerAdapter {

    public AdapterVP(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createForMain(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

}
