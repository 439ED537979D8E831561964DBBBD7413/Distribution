package com.yj.distribution;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yj.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author LK
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.title_content_layout)
    FrameLayout titleContentLayout;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.M_Vp)
    ViewPager MVp;
    @BindView(R.id.Home)
    RadioButton Home;
    @BindView(R.id.Order)
    RadioButton Order;
    @BindView(R.id.return_car)
    RadioButton returnCar;
    @BindView(R.id.My)
    RadioButton My;
    @BindView(R.id.Home_RG)
    RadioGroup HomeRG;
    View rootView, rootView2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        rootView = inflater.inflate(R.layout.search_layout, null);
        rootView2 = inflater.inflate(R.layout.string, null);
        titleContentLayout.addView(rootView2);
    }

    private void initRootView(String title) {

    }


    @OnClick(R.id.title_lift)
    public void onViewClicked() {
    }
}
