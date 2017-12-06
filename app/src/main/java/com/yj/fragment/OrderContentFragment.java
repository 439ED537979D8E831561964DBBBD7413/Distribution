package com.yj.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yj.adpter.HomeRecyAdapter;
import com.yj.base.BaseFragment;
import com.yj.distribution.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author LK
 */
public class OrderContentFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener, HomeRecyAdapter.OnViewClickListener {

    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    HomeRecyAdapter adapter;
    List<String> mlist = new ArrayList<>();

    public static OrderContentFragment newInstance(int type) {
        return newInstance(type, false);
    }

    public static OrderContentFragment newInstance(int type, boolean isFirstShow) {
        Bundle args = new Bundle();
        OrderContentFragment fragment = new OrderContentFragment();
        args.putInt("type", type);
        args.putBoolean("FirstShow", isFirstShow);
        fragment.setArguments(args);
        return fragment;
    }

    public int getType() {
        return getArguments().getInt("type");
    }

    public boolean isFirstShown() {
        return getArguments().getBoolean("isFirstShow");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ordel_content;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        swipeToLoadLayout.setRefreshing(true);
        swipeTarget.setLayoutManager(new LinearLayoutManager(mActivity));
//        adapter = new HomeRecyAdapter(mActivity, mlist, this);
//        swipeTarget.setAdapter(adapter);
    }


    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                //设置是否上拉加载+
                for (int i = 0; i < 10; i++) {
                    mlist.add("刷新" + i);
                }
                swipeToLoadLayout.setRefreshing(false);
                //adapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                //设置是否上拉刷新
                for (int i = 0; i < 10; i++) {
                    mlist.add("数据" + i);
                }
                //adapter.notifyDataSetChanged();
                if (null != swipeToLoadLayout) {
                    swipeToLoadLayout.setRefreshing(false);
                }
            }
        }, 1000);
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

    }
}
