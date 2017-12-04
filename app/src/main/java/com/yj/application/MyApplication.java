package com.yj.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;

/**
 * Created by LK on 2017/11/18.
 *
 * @author LK
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        OkGo.getInstance().init(this);
    }
}
