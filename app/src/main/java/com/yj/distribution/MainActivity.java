package com.yj.distribution;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yj.adpter.AdapterVP;
import com.yj.base.BaseActivity;
import com.yj.common.Constant;
import com.yj.fragment.HomeFragment;
import com.yj.fragment.OrderFragment;
import com.yj.fragment.ReturnCarFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author LK
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.title_content_layout)
    FrameLayout titleContentLayout;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.m_Vp)
    ViewPager mVp;
    @BindView(R.id.home_RG)
    RadioGroup homeRG;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    AdapterVP adapterVP;
    View rootView, rootView2;
    TextView titleView;
    EditText searchEdit;
    @BindView(R.id.navigationview)
    NavigationView navigationview;
    private long firstTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        rootView = inflater.inflate(R.layout.search_layout, null);
        rootView2 = inflater.inflate(R.layout.string, null);
        titleView = rootView2.findViewById(R.id.str_text);
        searchEdit = rootView.findViewById(R.id.search_edit);
        titleContentLayout.addView(rootView2);
        titleView.setText("配货统计");
        homeRG.setOnCheckedChangeListener(this);
        Fragment[] fragments = {new HomeFragment(), new OrderFragment(), new ReturnCarFragment()};
        adapterVP = new AdapterVP(getSupportFragmentManager(), fragments);
        mVp.setAdapter(adapterVP);
        mVp.addOnPageChangeListener(this);
        navigationview.setNavigationItemSelectedListener(this);
    }

    /**
     * 动态切换头部
     *
     * @param index
     */
    private void initRootView(int index) {
        switch (index) {
            case Constant.HOME:
                removeView(false);
                titleView.setText("配货统计");
                break;
            case Constant.ORDER:
                removeView(true);
                titleRight.setText("查找");
                break;
            case Constant.RETURNCAR:
                removeView(false);
                titleView.setText("退货车");
                break;
            default:
                break;
        }
    }

    private void removeView(boolean isSearchBox) {
        titleContentLayout.removeAllViews();
        if (isSearchBox) {
            titleContentLayout.addView(rootView);
        } else {
            titleContentLayout.addView(rootView2);
        }

    }

    @OnClick(R.id.title_lift)
    public void onViewClicked() {
        drawerlayout.openDrawer(Gravity.START);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.home:
                mVp.setCurrentItem(Constant.HOME);
                break;
            case R.id.order:
                mVp.setCurrentItem(Constant.ORDER);
                break;
            case R.id.return_car:
                mVp.setCurrentItem(Constant.RETURNCAR);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case Constant.HOME:
                homeRG.check(R.id.home);
                initRootView(Constant.HOME);
                break;
            case Constant.ORDER:
                homeRG.check(R.id.order);
                initRootView(Constant.ORDER);
                break;
            case Constant.RETURNCAR:
                homeRG.check(R.id.return_car);
                initRootView(Constant.RETURNCAR);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_news:
                break;
            default:
                break;
        }
        drawerlayout.closeDrawer(Gravity.START);
        return true;
    }

    /**
     * 程序按两次退出键退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > Constant.EXITTIME) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {//两次按键小于2秒时，退出应用  
                    System.exit(0);
                }
                break;
            default:
                break;

        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (drawerlayout.isDrawerOpen(Gravity.START)) {
            drawerlayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }

    }
}
