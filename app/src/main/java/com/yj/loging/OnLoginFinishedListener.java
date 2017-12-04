package com.yj.loging;

import android.os.Bundle;

/**
 * Created by Anthony on 2016/2/15.
 * Class Note:登陆事件监听
 *
 * @author LK
 */
public interface OnLoginFinishedListener {

    void onError(String msg);

    void onSuccess(Bundle bundle);
}
