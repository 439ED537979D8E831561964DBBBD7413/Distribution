package com.yj.adpter;

import android.content.Context;

import com.yj.bean.OrderItem;
import com.yj.distribution.R;

/**
 * Created by LK on 2017/12/7.
 *
 * @author LK
 */

public class OrderRecyAdapter extends CommonAdapter<OrderItem> {
    public OrderRecyAdapter(Context context) {
        super(context);
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.homecontentview;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OrderItem orderItem = list.get(position);

    }
}
