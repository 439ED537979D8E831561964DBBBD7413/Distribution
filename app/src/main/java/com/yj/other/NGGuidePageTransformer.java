package com.yj.other;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by LK on 2017/12/1.
 *
 * @author LK
 *         viewpager 动画
 */

public class NGGuidePageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_ALPHA = 0.0f;    //最小透明度

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();    //得到view宽

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left. 出了左边屏幕
            page.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            if (position < 0) {
                //消失的页面
                page.setTranslationX(-pageWidth * position);  //阻止消失页面的滑动
            } else {
                //出现的页面
                page.setTranslationX(pageWidth);        //直接设置出现的页面到底
                page.setTranslationX(-pageWidth * position);  //阻止出现页面的滑动
            }
            // Fade the page relative to its size.
            float alphaFactor = Math.max(MIN_ALPHA, 1 - Math.abs(position));
            //透明度改变Log
            page.setAlpha(alphaFactor);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.    出了右边屏幕
            page.setAlpha(0);
        }
    }

    int nowPostion = 0; //当前页面
    Context context;
    Fragment[] fragments;

    public void setCurrentItem(Context context, int nowPostion, Fragment[] fragments) {
        this.nowPostion = nowPostion;
        this.context = context;
        this.fragments = fragments;
    }

    public void setCurrentItem(int nowPostion) {
        this.nowPostion = nowPostion;
    }
}
