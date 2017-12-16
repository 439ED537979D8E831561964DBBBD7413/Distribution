package com.yj.mvp.home;

import java.util.List;

/**
 * Created by LK on 2017/12/8.
 *
 * @author LK
 */

public class HomeContract {

    public interface Model {
        void receiveParams(int ags1, int ags2, int ags3, int page);

        void requestData();

        void requestData2();
    }

    public interface View<T, V> {
        void setError(String mag);

        void refreshContentView(V v);

        void refreshContentView(List<T> contentList);

        void setCurrentItem(int position);

        void setRefreshing();

        void setDate(List<String> list);
    }

    public interface Presenter {

        //下拉刷新请求
        void requestRefresh();

        //加载更多数据
        void requestLoadMore();

        //点击事件
        void onItemClickListener(android.view.View view, int position);
    }

    public interface InteractionListener<T, V> {

        //请求成功
        void onInteractionSuccess(V v);

        void onInteractionSuccess(List<T> t);

        //请求失败
        void onInteractionFail(String errorMsg);

        void getData(List<String> list);
    }

}
