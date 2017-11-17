package com.yj.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yjkj.Utils.ImageLoaderManager;
import com.yjkj.ddps.R;
import com.yjkj.model.CartBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class CartAdapter extends CommonBaseAdapter<CartBean> {
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;

    public CartAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = minflater.inflate(R.layout.lv_commodity, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final CartBean cartBean = list.get(position);
        holder.itemCb.setText(cartBean.getSPtype());
        if (cartBean.isChoosed) {
            holder.itemCb.setChecked(true);
        } else {
            holder.itemCb.setChecked(false);
        }
        ImageLoaderManager.newInstance(context).showImage(cartBean.getImageUrl(), holder.itemImage);
        holder.price.setText("单价：￥" + cartBean.getPrice());
        holder.spName.setText(cartBean.getShoppingName());
        holder.varCode.setText("条码：" + cartBean.getId());
        holder.tvCommodityShowNum.setText(cartBean.getCount() + "");
        holder.amount.setText(cartBean.getCount() * cartBean.getPrice() + "");
        holder.itemCb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cartBean.setChoosed(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
                    }
                }
        );
        //增加按钮
        holder.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, holder.tvCommodityShowNum, holder.itemCb.isChecked());//暴露增加接口
            }
        });
        //删减按钮
        holder.tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, holder.tvCommodityShowNum, holder.itemCb.isChecked());//暴露删减接口
            }
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.item_cb)
        CheckBox itemCb;
        @BindView(R.id.item_image)
        ImageView itemImage;
        @BindView(R.id.sp_name)
        TextView spName;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.var_code)
        TextView varCode;
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.tv_sub)
        TextView tvSub;
        @BindView(R.id.tv_commodity_show_num)
        TextView tvCommodityShowNum;
        @BindView(R.id.tv_add)
        TextView tvAdd;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 单选接口
     *
     * @param checkInterface
     */
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    /**
     * 改变商品数量接口
     *
     * @param modifyCountInterface
     */
    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }


    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param position      元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int position, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param position      元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int position, View showCountView, boolean isChecked);

    }
}
