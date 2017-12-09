package com.yj.mvp.home.presenter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import com.yj.bean.DataOrderNum;
import com.yj.bean.OrderItem;
import com.yj.distribution.R;
import com.yj.mvp.mvpbase.BasePresenter;
import com.yj.mvp.home.contract.HomeContract;
import com.yj.mvp.home.model.HomeModellmpl;
import com.yj.util.DateUtil;

import java.util.List;

/**
 * Created by LK on 2017/12/7.
 *
 * @author LK
 */

public class HomePresenterlmpl extends BasePresenter<HomeContract.View<OrderItem, DataOrderNum>> implements HomeContract.Presenter
        , HomeContract.InteractionListener<OrderItem, DataOrderNum> {
    private HomeContract.View<OrderItem, DataOrderNum> view;
    private HomeContract.Model model;
    private Context mContext;
    private boolean isLoading = false;
    private boolean isLoadMore = false;
    private int page;

    public HomePresenterlmpl(Context context, HomeContract.View<OrderItem, DataOrderNum> view) {
        this.view = view;
        this.mContext = context;
        model = new HomeModellmpl(this, context);
    }

    @Override
    public synchronized void requestRefresh() {
        if (isLoading) return;
        page = 1;
        isLoading = true;
        model.requestData();
        model.requestData2();
    }

    @Override
    public synchronized void requestLoadMore() {
        if (isLoadMore) return;
        page++;
        isLoadMore = true;
        model.requestData2();
    }

    @Override
    public void onItemClickListener(View views, int position) {
        if (position == 0) {
            showDatePickerDialog();
        } else {
            switch (views.getId()) {
                case R.id.show_hideView:
                    view.setCurrentItem(position);
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

    @Override
    public void start() {
        view.setDate(DateUtil.getDate());
    }


    @Override
    public void onInteractionSuccess(DataOrderNum dataOrderNum) {
        view.refreshContentView(dataOrderNum);
        isLoadMore = false;
        isLoading = false;
    }

    @Override
    public void onInteractionSuccess(List<OrderItem> t) {
        view.refreshContentView(t);
        isLoading = false;
        isLoadMore = false;
    }

    @Override
    public void onInteractionFail(String errorMsg) {
        view.setError(errorMsg);
        isLoading = false;
        isLoadMore = false;
    }

    @Override
    public void getData(List<String> list) {
        view.setDate(list);
    }

    private void showDatePickerDialog() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog dialog = new DatePickerDialog(mContext, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker views, int year, int month, int dayOfMonth) {
                    model.receiveParams(year, ++month, dayOfMonth, page);
                    view.setRefreshing();
                }
            });
            dialog.show();
        }
    }
}
