package com.yj.util;

import com.google.gson.Gson;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GsonUtil<T> {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> T JsonArray2JavaList(String result, Type typeOfT) {
        Gson gson = new Gson();
        if (result == null) {
            return null;
        } else {
            StringReader reader = new StringReader(result);
            T target = gson.fromJson(reader, typeOfT);
            return target;
        }
    }

    public static String Java2Json(Object obj) {
        Gson gson = new Gson();
        String result = gson.toJson(obj);
        return result;
    }

    @SuppressWarnings({"rawtypes"})
    public static String JavaList2Json(ArrayList list) {
        Gson gson = new Gson();
        String result = gson.toJson(list);
        return result;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Object Json2Java(String result, Class tClass) {
        Gson gson = new Gson();
        return gson.fromJson(result, tClass);
    }


}