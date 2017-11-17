package com.yj.adpter;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yjkj.Utils.DateUtil;
import com.yjkj.ddps.R;
import com.yjkj.model.Goods;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class MyAdapter extends CommonBaseAdapter<Goods> implements View.OnClickListener {

    private int currentItem = -1;
    private Callback callback;

    public MyAdapter(Context context, Callback callback) {
        super(context);
        this.callback = callback;
    }

    @Override
    public void onClick(View view) {
        callback.click(view);
    }

    public interface Callback {
        void click(View v);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.lv_item_layout, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Goods g = list.get(i);
        holder.tvOrderid.setText("订单号：" + g.getOrderId());
        holder.tvTime.setText(DateUtil.getMillon(Long.parseLong(g.getTime())));
        if (g.getType() == 1) {
            holder.orderState.setTextColor(Color.parseColor("#E21918"));
            holder.orderState.setText("待审核");
        } else if (g.getType() == 2) {
            holder.orderState.setTextColor(Color.parseColor("#F7E48E"));
            holder.orderState.setText("正在配送");
        } else if (g.getType() == 3) {
            holder.orderState.setTextColor(Color.parseColor("#05ABF7"));
            holder.orderState.setText("配送成功");
        }
        holder.callPhone.setOnClickListener(this);
        holder.callPhone.setTag(i);
        holder.goodsReturn.setOnClickListener(this);
        holder.goodsReturn.setTag(i);
        holder.confirmAnOrder.setOnClickListener(this);
        holder.confirmAnOrder.setTag(i);
        holder.distrSucceed.setOnClickListener(this);
        holder.distrSucceed.setTag(i);
        holder.showItem.setTag(i);
        if (currentItem == i) {
            holder.llHide.setVisibility(View.VISIBLE);
        } else {
            holder.llHide.setVisibility(View.GONE);
        }
        holder.showItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = (int) view.getTag();
                if (tag == currentItem) {
                    currentItem = -1;
                } else {
                    currentItem = tag;
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_orderid)
        TextView tvOrderid;
        @BindView(R.id.order_state)
        TextView orderState;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.show_item)
        LinearLayout showItem;
        @BindView(R.id.hide_tv_orderId)
        TextView hideTvOrderId;
        @BindView(R.id.hide_tv_time)
        TextView hideTvTime;
        @BindView(R.id.hide_tv_sealnum)
        TextView hideTvSealnum;
        @BindView(R.id.hide_tv_address)
        TextView hideTvAddress;
        @BindView(R.id.hide_tv_phone)
        TextView hideTvPhone;
        @BindView(R.id.hide_tv_manery)
        TextView hideTvManery;
        @BindView(R.id.hide_tv_ramark)
        TextView hideTvRamark;
        @BindView(R.id.distr_succeed)
        TextView distrSucceed;
        @BindView(R.id.ll_item1)
        LinearLayout llItem1;
        @BindView(R.id.hide_tv_count)
        TextView hideTvCount;
        @BindView(R.id.gps)
        TextView gps;
        @BindView(R.id.call_phone)
        TextView callPhone;
        @BindView(R.id.goods_return)
        TextView goodsReturn;
        @BindView(R.id.confirm_an_order)
        TextView confirmAnOrder;
        @BindView(R.id.ll_hide)
        RelativeLayout llHide;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
