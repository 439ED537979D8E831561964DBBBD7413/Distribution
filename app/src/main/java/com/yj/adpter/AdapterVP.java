package com.yj.adpter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Administrator on 2017/7/22 0022.
 */

public class AdapterVP extends FragmentPagerAdapter {

    public AdapterVP(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                HomeFragment Hfragment = new HomeFragment();
//                return Hfragment;
//            case 1:
//                OrderFragment ofragment = new OrderFragment();
//                return ofragment;
//            case 2:
//                ReturnFragment rfragment = new ReturnFragment();
//                return rfragment;
//            case 3:
//                MyFragment mfragment = new MyFragment();
//                return mfragment;
//        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
