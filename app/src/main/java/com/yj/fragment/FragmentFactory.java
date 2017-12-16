package com.yj.fragment;


import android.support.v4.app.Fragment;

import com.yj.common.Constant;
import com.yj.mvp.home.HomeFragment;
import com.yj.mvp.order.OrderContentFragment;

/**
 * Created by LK on 2017/12/16.
 *
 * @author LK
 */

public class FragmentFactory {
    /**
     * main
     * @param position
     * @return
     */
    public static Fragment createForMain(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new OrderFragment();
            case 2:
                return new ReturnCarFragment();
            default:
                break;
        }
        return null;
    }

    /**
     * order
     * @param position
     * @return
     */
    public static Fragment createForOrder(int position) {
        return OrderContentFragment.newInstance(Constant.ORDERTYPE[position]);
    }
}
