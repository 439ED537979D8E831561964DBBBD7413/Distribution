package com.yj.mvp.mvpbase;

/**
 * Created by LK on 2017/12/8.
 *
 * @author LK
 */

public interface BaseView<P> {
    //这个可以在Activity中包裹Fragment的时候应用，这时候继承MVPBaseActivity
    //Activity中初始化Presenter的实例 ，然后通过view调用该方法将Presenter塞给Fragment
    void setPresenter(P presenter);

}
