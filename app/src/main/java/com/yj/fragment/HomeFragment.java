package com.yj.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yj.adpter.HomeRecyAdapter;
import com.yj.base.BaseFragment;
import com.yj.bean.DataOrderNum;
import com.yj.bean.OrderItem;
import com.yj.distribution.R;
import com.yj.util.ShowLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author LK
 */
public class HomeFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener, HomeRecyAdapter.OnViewClickListener {

    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    List<String> mlist = new ArrayList<>();
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    HomeRecyAdapter adapter;
    private int mPageMark;
    private List<DataOrderNum> orderNums = new ArrayList<>();
    private List<OrderItem> orderItem = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        //ShowLog.e(PreferenceUtils.getPrefString(mActivity, Constant.TOKEN, ""));
        swipeTarget.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new HomeRecyAdapter(mActivity, mlist, this);
        swipeTarget.setAdapter(adapter);
        //swipeToLoadLayout.setRefreshing(true);

    }


    @Override
    public void onLoadMore() {
        //上拉加载
        //swipeToLoadLayout.setLoadingMore(false);
        // mPageMark++;
        // requestData();
    }

    @Override
    public void onRefresh() {
        //设置是否下拉刷新
        //mPageMark = 1;
//        if (null != swipeToLoadLayout) {
//            swipeToLoadLayout.setRefreshing(false);
//        }

    }

    private void requestData() {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        ShowLog.e("点击的是" + position);
    }


}
