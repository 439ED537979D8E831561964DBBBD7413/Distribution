package com.yj.other;

/**
 * Created by LK on 2017/12/4.
 *
 * @author LK
 */

public class MyThrowable extends Throwable {
    String info;

    public MyThrowable(String info) {
        this.info = info;
    }

    @Override
    public String getMessage() {
        return info;
    }
}
