package com.yj.mvp.order;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.yj.bean.OrderItem;
import com.yj.common.CommonUtils;
import com.yj.common.Constant;
import com.yj.mvp.order.OrderContract;
import com.yj.other.MyThrowable;
import com.yj.util.ShowLog;

import java.util.List;

/**
 * Created by LK on 2017/12/9.
 *
 * @author LK
 */

public class OrderModellmpl implements OrderContract.Model {
    private OrderContract.OnSuccessListener<OrderItem> listener;
    private int mType;
    private Context mContext;

    public OrderModellmpl(OrderContract.OnSuccessListener<OrderItem> listener, Context context) {
        this.listener = listener;
        this.mContext = context;
    }

    @Override
    public void requestData(int page) {
        ShowLog.e(mType + "");
        if (CommonUtils.isNetworkConnected(mContext)) {
            OkGo.<List<OrderItem>>post(Constant.BASEURL + "Appd/order")
                    .tag(this)
                    .params("p", page)
                    .params("status", mType)
                    .execute(new AbsCallback<List<OrderItem>>() {
                        @Override
                        public void onSuccess(Response<List<OrderItem>> response) {
                            listener.onSuccess(response.body());
                        }

                        @Override
                        public void onError(Response<List<OrderItem>> response) {
                            super.onError(response);
                            listener.onError(response.getException().getMessage());
                        }

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
                    });
        } else {
            listener.onError("网络异常");
        }
    }

    @Override
    public void setType(int type) {
        mType = type;
    }
}
