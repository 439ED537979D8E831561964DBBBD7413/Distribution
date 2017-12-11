package com.yj.adpter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yj.bean.OrderItem;
import com.yj.distribution.R;
import com.yj.util.DateUtil;

/**
 * Created by LK on 2017/12/7.
 *
 * @author LK
 */

public class OrderRecyAdapter extends CommonAdapter<OrderItem> implements View.OnClickListener {
    private int currentItems = -1;
    private OnViewClickListener listener;

    public OrderRecyAdapter(Context context, OnViewClickListener listener) {
        super(context);
        this.listener = listener;
    }

    public void setCurrentItem(int currentItem) {
        if (currentItems != currentItem) {
            currentItems = currentItem;
        } else {
            currentItems = -1;
        }
        notifyDataSetChanged();
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.homecontentview;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OrderItem data = list.get(position);
        holder.getTextView(R.id.order_number).setText(String.format("订单号:%s", data.getMainorder()));
        holder.getTextView(R.id.order_state).setText("可配送");
        holder.getTextView(R.id.order_time).setText(String.format("下单时间：%s", DateUtil.getDateToString(data.getAddtime())));
        holder.getTextView(R.id.class_num).setText(data.getClassX());
        holder.getTextView(R.id.number).setText(data.getNum());
        holder.getTextView(R.id.money).setText(data.getMoney() + "");
        holder.getTextView(R.id.Seal_number).setText(String.format("封\b\b签\b\b号\b：%s", data.getMainorder()));
        holder.getTextView(R.id.Delivery_address).setText(String.format("送货地址：%s", data.getShopname()));
        holder.getTextView(R.id.phone).setText(String.format("联系电话：%s", data.getMobile()));
        holder.getTextView(R.id.customer_name).setText(String.format("客户名称：%s", data.getContacts()));
        holder.getTextView(R.id.moneys).setText(String.format("金额：%s元", data.getMoney()));
        //导航
        TextView myNavigation = holder.getTextView(R.id.myNavigation);
        myNavigation.setTag(position);
        myNavigation.setOnClickListener(this);
        //打电话
        TextView dial_phone = holder.getTextView(R.id.dial_phone);
        dial_phone.setTag(position);
        dial_phone.setOnClickListener(this);
        //退货
        TextView return_shop = holder.getTextView(R.id.return_shop);
        return_shop.setTag(position);
        return_shop.setOnClickListener(this);
        //确定订单
        TextView sure_order = holder.getTextView(R.id.sure_order);
        sure_order.setTag(position);
        sure_order.setOnClickListener(this);
        TextView order_number = holder.getTextView(R.id.order_number);
        order_number.setTag(position);
        order_number.setOnClickListener(this);
        //控制点击下拉
        View showHideView = holder.get(R.id.show_hideView);
        showHideView.setTag(position);
        showHideView.setOnClickListener(this);
        View hideView = holder.get(R.id.hideViewLayout);
        if (currentItems == position) {
            hideView.setVisibility(View.VISIBLE);
        } else {
            hideView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v, (Integer) v.getTag());
    }

    public interface OnViewClickListener {
        void onItemClick(View view, int position);
    }
}
