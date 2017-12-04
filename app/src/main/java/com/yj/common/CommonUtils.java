/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yj.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

/**
 * Description: 公共工具类
 */
public class CommonUtils {
    /**
     * 获取屏幕宽
     */
    public static int screenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高
     */
    public static int screenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * return if str is empty
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim().replace(" ", "");
        if (str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 得到版本名称
     *
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
        return packageInfo.versionName;
    }

    /**
     * 得到版本号
     *
     * @param context
     * @return
     */
    public static int getVerCode(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return 1;
        }
        return packageInfo.versionCode;
    }


    /**
     * @param context
     * @param activity 目标Activity
     * @param bundle   携带的数据
     * @description: Activity跳转
     */
    public static void goActivity(Context context, Class<?> activity, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param activity 目标Activity
     * @param bundle   携带的数据
     * @param isFinish
     * @description: Activity跳转
     */
    public static void goActivity(Context context, Class<?> activity, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent();
        intent.setClass(context, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }

    /**
     * @param context
     * @param activity    目标Activity
     * @param bundle      携带的数据
     * @param requestCode 请求码
     * @param isFinish
     * @description: Activity跳转, 带返回结果
     */
    public static void goActivityForResult(Context context, Class<?> activity, Bundle bundle, int requestCode, boolean isFinish) {
        Intent intent = new Intent();
        intent.setClass(context, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(intent, requestCode);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }

    /**
     * @param context
     * @param bundle     携带的数据
     * @param resultCode 返回标示码
     * @description: ForResult跳转, 带返回结果
     */
    public static void goResult(Context context, Bundle bundle, int resultCode) {
        Intent intent = ((Activity) context).getIntent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ((Activity) context).setResult(resultCode, intent);
        ((Activity) context).finish();
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();

            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


}
