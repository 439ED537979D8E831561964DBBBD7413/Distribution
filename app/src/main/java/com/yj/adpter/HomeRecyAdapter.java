package com.yj.adpter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yj.distribution.R;

import java.util.List;

/**
 * Created by LK on 2017/11/22.
 *
 * @author LK
 */

public class HomeRecyAdapter extends CommonAdapter<String> implements View.OnClickListener {
    private OnViewClickListener listener;
    public static final int TYPE_HEAD = 0;
    public static final int TYPE_CONTENT = 1;

    public HomeRecyAdapter(Context context, List list, OnViewClickListener listener) {
        super(context, list);
        this.listener = listener;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        if (viewType == TYPE_HEAD) {
            return R.layout.homeheadview;
        } else {
            return R.layout.string;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textView1 = holder.getTextView(R.id.str_text);
        textView1.setText(list.get(position));
        textView1.setTag(position);
        textView1.setOnClickListener(this);

    }

    @Override
    public int getType(int position) {
        if (position == -1) {
            return TYPE_HEAD;
        } else {
            return TYPE_CONTENT;
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
