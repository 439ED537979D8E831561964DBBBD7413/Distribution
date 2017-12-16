package com.yj.mvp.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yj.adpter.HomeRecyAdapter;
import com.yj.bean.DataOrderNum;
import com.yj.bean.OrderItem;
import com.yj.distribution.R;
import com.yj.mvp.mvpbase.MVPBaseFragment;
import com.yj.mvp.home.HomeContract;
import com.yj.mvp.home.HomePresenterlmpl;
import com.yj.other.DDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author LK
 */
public class HomeFragment extends MVPBaseFragment<HomeContract.View<OrderItem, DataOrderNum>, HomePresenterlmpl> implements HomeContract.View<OrderItem, DataOrderNum>
        , OnRefreshListener, OnLoadMoreListener, HomeRecyAdapter.OnViewClickListener {
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private HomeRecyAdapter adapter;
    private List<OrderItem> contentLists = new ArrayList<>();

    @Override
    public void setError(String mag) {
        showToast(mag);
        onPauseSwipeLayout();
    }

    @Override
    public void refreshContentView(DataOrderNum dataOrderNum) {
        adapter.setHeadlist(dataOrderNum);
    }

    @Override
    public void refreshContentView(List<OrderItem> contentList) {
        contentLists.addAll(contentList);
        adapter.setList(contentLists);
        adapter.notifyDataSetChanged();
        onPauseSwipeLayout();
    }

    @Override
    protected HomePresenterlmpl createPresenter() {
        return new HomePresenterlmpl(mActivity, this);
    }

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
        if (swipeTarget != null) {
            adapter = new HomeRecyAdapter(mActivity, this);
            swipeTarget.setLayoutManager(new LinearLayoutManager(mActivity));
            swipeTarget.setAdapter(adapter);
            swipeTarget.addItemDecoration(new DDecoration(mActivity, getResources().getDrawable(R.drawable.decoration)));
        }
        mPresenter.start();
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        }, 500);
    }

    @Override
    public void onLoadMore() {
        mPresenter.requestLoadMore();
    }

    @Override
    public void onRefresh() {
        mPresenter.requestRefresh();
        contentLists.clear();
    }

    @Override
    public void onItemClick(View view, int position) {
        mPresenter.onItemClickListener(view, position);
    }

    @Override
    public void setCurrentItem(int position) {
        adapter.setCurrentItem(position);
    }

    @Override
    public void setRefreshing() {
        swipeToLoadLayout.setRefreshing(true);
    }

    @Override
    public void setDate(List<String> list) {
        adapter.setTimes(list);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        onPauseSwipeLayout();
    }

    public void onPauseSwipeLayout() {
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
    }
}
