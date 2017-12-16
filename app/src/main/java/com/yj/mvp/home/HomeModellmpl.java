package com.yj.mvp.home;

import android.annotation.SuppressLint;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.yj.bean.DataOrderNum;
import com.yj.bean.OrderItem;
import com.yj.common.CommonUtils;
import com.yj.common.Constant;
import com.yj.mvp.home.HomeContract;
import com.yj.other.MyThrowable;
import com.yj.util.ShowLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LK on 2017/12/7.
 *
 * @author LK
 */

public class HomeModellmpl implements HomeContract.Model {
    private HomeContract.InteractionListener<OrderItem, DataOrderNum> listener;
    private int mPageMark;
    private Context mContext;
    private Map<String, String> params = new HashMap<>();
    private String strData;
    private List<String> time = new ArrayList<>();

    public HomeModellmpl(HomeContract.InteractionListener<OrderItem, DataOrderNum> listener, Context context) {
        this.listener = listener;
        this.mContext = context;
    }


    @SuppressLint("DefaultLocale")
    @Override
    public void receiveParams(int ags1, int ags2, int ags3, int page) {
        this.mPageMark = page;
        params.put("year", ags1 + "");
        params.put("month", ags1 + "");
        params.put("day", ags1 + "");
        time.add(ags1 + "");
        time.add(ags2 + "");
        time.add(ags3 + "");
        listener.getData(time);
        strData = String.format("%1$d-%2$d-%3$d", ags1, ags2, ags3);
    }

    @Override
    public void requestData() {
        if (CommonUtils.isNetworkConnected(mContext)) {
            OkGo.<DataOrderNum>post(Constant.BASEURL + "Appd/order_date")
                    .params(params)
                    .tag(this)
                    .execute(new AbsCallback<DataOrderNum>() {
                        @Override
                        public void onSuccess(Response<DataOrderNum> response) {
                            listener.onInteractionSuccess(response.body());
                        }

                        @Override
                        public void onError(Response<DataOrderNum> response) {
                            super.onError(response);
                            listener.onInteractionFail(response.getException().getMessage());
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

                    });
        } else {
            listener.onInteractionFail("网络异常");
        }
    }

    @Override
    public void requestData2() {
        if (CommonUtils.isNetworkConnected(mContext)) {
            OkGo.<List<OrderItem>>post(Constant.BASEURL + "Appd/order_chart")
                    .tag(this)
                    .params("data", strData)
                    .params("p", mPageMark)
                    .execute(new AbsCallback<List<OrderItem>>() {
                        @Override
                        public List<OrderItem> convertResponse(okhttp3.Response response) throws Throwable {
                            String json = response.body().string();
                            ShowLog.e(json);
                            if (json != null) {
                                return JSONArray.parseArray(json, OrderItem.class);
                            } else {
                                throw new MyThrowable("无数据");
                            }
                        }

                        @Override
                        public void onError(Response<List<OrderItem>> response) {
                            super.onError(response);
                            listener.onInteractionFail(response.getException().getMessage());
                        }

                        @Override
                        public void onSuccess(Response<List<OrderItem>> response) {
                            listener.onInteractionSuccess(response.body());
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();

                        }
                    });
        } else {
            listener.onInteractionFail("网络异常");
        }
    }
}
