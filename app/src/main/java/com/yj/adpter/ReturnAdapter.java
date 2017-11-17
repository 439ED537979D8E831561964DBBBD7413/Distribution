package com.yj.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yjkj.ddps.R;
import com.yjkj.model.Goods;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class ReturnAdapter extends CommonBaseAdapter {
    public ReturnAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder Holder;
        if (view == null) {
            view = minflater.inflate(R.layout.return_lv_item, viewGroup, false);
            Holder = new ViewHolder(view);
            view.setTag(Holder);
        } else {
            Holder = (ViewHolder) view.getTag();
        }
        Goods item = (Goods) list.get(i);
        Holder.tvOrderid.setText("退货单号：" + item.getOrderId());
        Holder.orderState.setText("数量：" + item.getNum() + "件");
        Holder.tvTime.setText("时间：" + item.getTime());
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.tv_orderid)
        TextView tvOrderid;
        @BindView(R.id.order_state)
        TextView orderState;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
