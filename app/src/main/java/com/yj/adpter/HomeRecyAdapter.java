package com.yj.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yj.bean.DataOrderNum;
import com.yj.bean.OrderItem;
import com.yj.distribution.R;
import com.yj.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LK on 2017/11/22.
 *
 * @author LK
 */

public class HomeRecyAdapter extends CommonAdapter<OrderItem> implements View.OnClickListener {
    private OnViewClickListener listener;
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_CONTENT = 1;
    private DataOrderNum headList;
    private List<String> time = new ArrayList<>();
    private int currentItems = -1;

    public void setCurrentItem(int currentItem) {
        if (currentItems != currentItem) {
            currentItems = currentItem;
        } else {
            currentItems = -1;
        }
        notifyDataSetChanged();
    }

    public HomeRecyAdapter(Context context, OnViewClickListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        switch (viewType) {
            case TYPE_HEAD:
                return R.layout.homeheadview;
            case TYPE_CONTENT:
                return R.layout.homecontentview;
            default:
                break;
        }
        return -1;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0 ) {
            if (headList!= null) {
                DataOrderNum dataOrderNum = headList;
                holder.getTextView(R.id.tv_year).setText(time.get(0) + " 年");
                holder.getTextView(R.id.tv_month).setText(time.get(1) + " 月");
                holder.getTextView(R.id.tv_day).setText(time.get(2) + " 日");
                holder.getTextView(R.id.total).setText(String.format("总计:%d元", dataOrderNum.getYear().getMoney()));
                holder.getTextView(R.id.month_add).setText(String.format("合计:%d元", dataOrderNum.getMonth().getMoney()));
                holder.getTextView(R.id.subtotal).setText(String.format("小计:%d元", dataOrderNum.getDay().getMoney()));
                holder.getTextView(R.id.year_ticket).setText(String.format("票数:%s", dataOrderNum.getYear().getNum()));
                holder.getTextView(R.id.month_ticket).setText(String.format("票数:%s", dataOrderNum.getMonth().getNum()));
                holder.getTextView(R.id.day_ticket).setText(String.format("票数:%s", dataOrderNum.getDay().getNum()));
                View timeView = holder.get(R.id.timeView);
                timeView.setTag(position);
                timeView.setOnClickListener(this);
            }
        } else {
            OrderItem data = list.get(position - 1);
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
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else {
            return TYPE_CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size() + 1;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v, (Integer) v.getTag());

    }

    public interface OnViewClickListener {
        void onItemClick(View view, int position);
    }

    public void setHeadlist(DataOrderNum headlist) {
        this.headList = headlist;
    }

    public void setTimes(List<String> time) {
        this.time = time;
    }
}
