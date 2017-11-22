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

public class HomeRecyAdapter extends CommonAdapter<String>implements View.OnClickListener{
    private OnViewClickListener listener;

    public HomeRecyAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.string;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textView1 = holder.getTextView(R.id.str_text);
        textView1.setText(list.get(position));
        textView1.setTag(position);
        textView1.setOnClickListener(this);

    }

    public void setOnViewClickListener(OnViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v, (Integer) v.getTag());

    }

    public interface OnViewClickListener {
        void onItemClick(View view, int position);
    }
}
