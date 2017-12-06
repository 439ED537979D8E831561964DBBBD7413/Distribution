package com.yj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class DateUtil {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /*时间戳转换成字符窜*/
    public static String getDateToString(String str) {
        try {
            if (str.isEmpty()) {
                return "";
            }
            long time = Long.parseLong(str)*1000;
            Date d = new Date(time);
            return datetimeFormat.format(d);
        } catch (Exception e) {
            return "";
        }
    }
}

