package com.yj.mvp.order;

import java.util.List;

/**
 * Created by LK on 2017/12/9.
 *
 * @author LK
 */

public class OrderContract {
    public interface Model {
        void requestData(int page);

        void setType(int type);
    }

    public interface View<T> {
        void setError(String mag);

        void refreshContentView(List<T> contentList);

        void setCurrentItem(int position);

        void setRefreshing();
    }

    public interface Presenter {
        //设置类型
        void setType(int type);

        //下拉刷新请求
        void requestRefresh();

        //加载更多数据
        void requestLoadMore();

        //点击事件
        void onItemClickListener(android.view.View view, int position);
    }

    public interface OnSuccessListener<T> {
        void onSuccess(List<T> tList);

        void onError(String msg);
    }
}
