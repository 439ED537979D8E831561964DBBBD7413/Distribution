package com.yj.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.yj.adpter.HomeRecyAdapter;
import com.yj.base.BaseFragment;
import com.yj.bean.OrderItem;
import com.yj.common.CommonUtils;
import com.yj.common.Constant;
import com.yj.distribution.R;
import com.yj.util.ShowLog;

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
    private HomeRecyAdapter adapter;
    private int mPageMark;
    private List<OrderItem> orderItem = new ArrayList<>();

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
    }

    /**
     * 上啦加载
     */
    @Override
    public void onLoadMore() {
        mPageMark++;
        requestData();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        orderItem.clear();
        mPageMark = 1;
        requestData();
    }

    /**
     * 数据加载
     */
    private void requestData() {
        if (CommonUtils.isNetworkConnected(mActivity)) {
            OkGo.<OrderItem>post(Constant.BASEURL + "Appd/order")
                    .tag(this)
                    .params("p", mPageMark)
                    .params("status", getType())
                    .execute(new AbsCallback<OrderItem>() {
                        @Override
                        public void onSuccess(Response<OrderItem> response) {

                        }

                        @Override
                        public void onError(Response<OrderItem> response) {
                            super.onError(response);
                        }

                        @Override
                        public OrderItem convertResponse(okhttp3.Response response) throws Throwable {
                            ShowLog.e(response.body().string());
                            return null;
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            onPauseSwipeLayout();
                        }
                    });

        } else {
            showToast("无网络");
            onPauseSwipeLayout();
        }
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
}
