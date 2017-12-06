package com.yj.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpParams;
import com.yj.common.Constant;
import com.yj.util.PreferenceUtils;

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
        HttpParams params = new HttpParams();
        params.put("uid", PreferenceUtils.getPrefString(getApplicationContext(), Constant.UID, ""));
        params.put("token", PreferenceUtils.getPrefString(getApplicationContext(), Constant.TOKEN, ""));
        OkGo.getInstance().init(this)
                .setCacheMode(CacheMode.DEFAULT)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .addCommonParams(params);
    }
}
