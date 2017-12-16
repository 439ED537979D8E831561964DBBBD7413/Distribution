package com.yj.mvp.order;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.yj.adpter.OrderRecyAdapter;
import com.yj.bean.OrderItem;
import com.yj.distribution.R;
import com.yj.mvp.mvpbase.MVPBaseFragment;
import com.yj.other.DDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author LK
 */
public class OrderContentFragment extends MVPBaseFragment<OrderContract.View<OrderItem>, OrderPresenterlmpl> implements OrderContract.View<OrderItem>, OnRefreshListener, OnLoadMoreListener, OrderRecyAdapter.OnViewClickListener {

    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private OrderRecyAdapter adapter;
    private List<OrderItem> orderItem = new ArrayList<>();

    public static OrderContentFragment newInstance(int type) {
        Bundle args = new Bundle();
        OrderContentFragment fragment = new OrderContentFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    public int getType() {
        return getArguments().getInt("type");
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
        mPresenter.setType(getType());
        adapter = new OrderRecyAdapter(mActivity, this);
        swipeTarget.setLayoutManager(new LinearLayoutManager(mActivity));
        swipeTarget.setAdapter(adapter);
        swipeTarget.addItemDecoration(new DDecoration(mActivity, getResources().getDrawable(R.drawable.decoration)));
        swipeToLoadLayout.setRefreshing(true);

    }

    /**
     * 上啦加载
     */
    @Override
    public void onLoadMore() {
        mPresenter.requestLoadMore();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mPresenter.requestRefresh();
        orderItem.clear();
    }


    @Override
    public void onPause() {
        super.onPause();
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

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void setError(String errmsg) {
        showToast(errmsg);
        onPauseSwipeLayout();
    }

    @Override
    public void refreshContentView(List<OrderItem> contentList) {
        adapter.setList(contentList);
        onPauseSwipeLayout();
    }

    @Override
    public void setCurrentItem(int position) {
        adapter.setCurrentItem(position);
    }

    @Override
    public void setRefreshing() {

    }

    @Override
    protected OrderPresenterlmpl createPresenter() {
        return new OrderPresenterlmpl(this, mActivity);
    }
}
