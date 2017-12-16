package com.yj.mvp.order;

import android.content.Context;
import android.view.View;

import com.yj.bean.OrderItem;
import com.yj.mvp.mvpbase.BasePresenter;
import com.yj.mvp.order.OrderContract;
import com.yj.mvp.order.OrderModellmpl;

import java.util.List;

/**
 * Created by LK on 2017/12/9.
 *
 * @author LK
 */

public class OrderPresenterlmpl extends BasePresenter<OrderContract.View<OrderItem>> implements OrderContract.Presenter, OrderContract.OnSuccessListener<OrderItem> {
    private OrderContract.View<OrderItem> mView;
    private OrderContract.Model mModel;
    private boolean isLoading = false;
    private boolean isLoadMore = false;
    private int page;

    public OrderPresenterlmpl(OrderContract.View<OrderItem> view, Context context) {
        mView = view;
        mModel = new OrderModellmpl(this, context);
    }

    @Override
    public void setType(int type) {
        mModel.setType(type);
    }

    @Override
    public synchronized void requestRefresh() {
        if (isLoading) return;
        isLoading = true;
        page = 1;
        mModel.requestData(page);

    }

    @Override
    public synchronized void requestLoadMore() {
        if (isLoadMore) return;
        isLoadMore = true;
        page++;
        mModel.requestData(page);
    }

    @Override
    public void onItemClickListener(View view, int position) {

    }

    @Override
    public void start() {

    }


    @Override
    public void onSuccess(List<OrderItem> list) {
        mView.refreshContentView(list);
        isLoading = false;
        isLoadMore = false;
    }

    @Override
    public void onError(String msg) {
        mView.setError(msg);
        isLoading = false;
        isLoadMore = false;
    }
}
