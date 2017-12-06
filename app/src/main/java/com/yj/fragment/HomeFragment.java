package com.yj.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.yj.adpter.HomeRecyAdapter;
import com.yj.base.BaseFragment;
import com.yj.bean.DataOrderNum;
import com.yj.bean.OrderItem;
import com.yj.common.CommonUtils;
import com.yj.common.Constant;
import com.yj.distribution.R;
import com.yj.other.MyThrowable;
import com.yj.util.GsonUtil;
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
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    HomeRecyAdapter adapter;
    private int mPageMark;
    private List<DataOrderNum> orderNums = new ArrayList<>();
    private List<OrderItem> orderItem = new ArrayList<>();
    private List<String> tiem = new ArrayList<>();

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
        tiem.add("2017");
        tiem.add("12");
        tiem.add("6");
        if (swipeTarget != null) {
            adapter = new HomeRecyAdapter(mActivity, this);
            swipeTarget.setLayoutManager(new LinearLayoutManager(mActivity));
            swipeTarget.setAdapter(adapter);
            swipeTarget.setItemAnimator(new DefaultItemAnimator());
            swipeTarget.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        }
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        }, 500);

    }

    @Override
    public void onLoadMore() {
        orderNums.clear();
        //上拉加载
        mPageMark++;
        requestData();
        request2Data();
    }

    @Override
    public void onRefresh() {
        orderNums.clear();
        orderItem.clear();
        //设置是否下拉刷新
        mPageMark = 1;
        requestData();
        request2Data();
    }

    private void requestData() {
        if (CommonUtils.isNetworkConnected(mActivity)) {
            OkGo.<DataOrderNum>post(Constant.BASEURL + "Appd/order_date")
                    .params("year", "")
                    .params("month", "")
                    .params("day", "")
                    .tag(this)
                    .execute(new AbsCallback<DataOrderNum>() {
                        @Override
                        public void onSuccess(Response<DataOrderNum> response) {
                            orderNums.add(response.body());
                        }

                        @Override
                        public void onError(Response<DataOrderNum> response) {
                            super.onError(response);
                            showToast(response.getException().getMessage());
                            ShowLog.e(response.getException().getMessage());
                        }

                        @Override
                        public DataOrderNum convertResponse(okhttp3.Response response) throws Throwable {
                            String json = response.body().string();
                            ShowLog.e(json);
                            if (json != null) {
                                return JSON.parseObject(json, DataOrderNum.class);
                            } else {
                                throw new MyThrowable("无数据");
                            }
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            adapter.setHeadlist(orderNums);
                        }
                    });
        } else {
            showToast("无网络");
            onPauseSwipeLayout();
        }
    }

    public void request2Data() {
        if (CommonUtils.isNetworkConnected(mActivity)) {
            OkGo.<List<OrderItem>>post(Constant.BASEURL + "Appd/order_chart")
                    .tag(this)
                    .params("data", "")
                    .params("p", mPageMark)
                    .execute(new AbsCallback<List<OrderItem>>() {

                        @Override
                        public List<OrderItem> convertResponse(okhttp3.Response response) throws Throwable {
                            String json = response.body().string();
                            ShowLog.e(json);
                            if (json != null) {
                                orderItem = GsonUtil.JsonArray2JavaList(json, new TypeToken<List<OrderItem>>() {
                                }.getType());
                                return orderItem;
                            } else {
                                throw new MyThrowable("无数据");
                            }
                        }

                        @Override
                        public void onError(Response<List<OrderItem>> response) {
                            super.onError(response);
                            showToast(response.getException().getMessage());
                            ShowLog.e(response.getException().getMessage());
                        }

                        @Override
                        public void onSuccess(Response<List<OrderItem>> response) {
                            orderItem.addAll(response.body());
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            adapter.setTimes(tiem);
                            adapter.setList(orderItem);
                            adapter.notifyDataSetChanged();
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
        ShowLog.e("点击的是" + position);
        if (position == 0) {

        } else {
            switch (view.getId()) {
                case R.id.show_hideView:
                    adapter.setCurrentItem(position);
                    break;
                case R.id.myNavigation:
                    //导航
                    break;
                case R.id.dial_phone:
                    //打电话
                    break;
                case R.id.return_shop:
                    //退货
                    break;
                case R.id.sure_order:
                    //确定订单
                    break;
                case R.id.order_number:
                    //订单号
                    break;
                default:
                    break;
            }
        }
    }


}
