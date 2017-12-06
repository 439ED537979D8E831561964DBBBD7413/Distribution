package com.yj.loging.model;


import android.content.Context;
import android.os.Bundle;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.yj.bean.User;
import com.yj.common.CommonUtils;
import com.yj.common.Constant;
import com.yj.loging.OnLoginFinishedListener;
import com.yj.other.MyThrowable;
import com.yj.util.GsonUtil;
import com.yj.util.PreferenceUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LK
 */
public class LoginModelImpl implements LoginModel {

    @Override
    public void login(String username, String password, final OnLoginFinishedListener listener, final Context context) {

        if (username.equals("")) {
            listener.onError("账号未填写");
            return;
        }
        if (password.equals("")) {
            listener.onError("密码未填写");
            return;
        }
        Map<String, String> params = new HashMap<>(16);
        params.put("username", username);
        params.put("password", password);
        if (CommonUtils.isNetworkConnected(context)) {
            OkGo.<User>post(Constant.BASEURL + "Appdi/dologin")
                    .params(params)
                    .tag(this)
                    .execute(new AbsCallback<User>() {
                        @Override
                        public void onSuccess(Response<User> response) {
                            User user = response.body();
                            PreferenceUtils.setPrefString(context, Constant.UID, user.getUid());
                            PreferenceUtils.setPrefString(context, Constant.TOKEN, user.getToken());
                            PreferenceUtils.setPrefString(context, Constant.USERNAME, user.getUsername());
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("user", user);
                            listener.onSuccess(bundle);
                        }

                        @Override
                        public void onError(Response<User> response) {
                            super.onError(response);
                            listener.onError(response.getException().getMessage());
                        }

                        @Override
                        public User convertResponse(okhttp3.Response response) throws Throwable {
                            String json = response.body().string();
                            final JSONObject object = new JSONObject(json);
                            String code = object.optString("errcode");
                            if (code.equals("01")) {
                                throw new MyThrowable(object.optString("info"));
                            } else {
                                //转换对象
                                User user = (User) GsonUtil.Json2Java(json, User.class);
                                return user;
                            }
                        }
                    });
        } else {
            listener.onError("网络异常");
        }
    }

    @Override
    public void onDestroys() {
        OkGo.getInstance().cancelTag(this);
    }

}
