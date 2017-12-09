package com.yj.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
            long time = Long.parseLong(str) * 1000;
            Date d = new Date(time);
            return datetimeFormat.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回当前年月日
     *
     * @return
     */
    public static List<String> getDate() {
        Calendar cld = Calendar.getInstance();
        int years = cld.get(Calendar.YEAR);       //获取年月日时分秒
        int months = cld.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        int days = cld.get(Calendar.DAY_OF_MONTH);
        List<String> list = new ArrayList<>();
        list.add(years+"");
        list.add(months+"");
        list.add(days+"");
        return list;
    }

    /**
     * 获取系统当前时间的秒数
     *
     * @return
     */
    public static Long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * mm-dd mm-ss
     *
     * @return
     */
    public static String getDateFormat(String format) {
        long time = getCurrentTimeMillis();
        Date d = new Date(time);
        return new SimpleDateFormat(format).format(d);
    }

}

