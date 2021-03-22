package com.phynos.framework.front.raw.util;

import com.alibaba.fastjson.JSON;

import java.io.*;

/**
 * @author by Lupc
 * @date 2020/4/5.
 */
public class JsonUtil {

    public static <T> T jsonBytes2Object(byte[] bytes, Class<T> tClass) {
        //尽量把对象转换成JSON保存更稳妥
        try {
            String json = new String(bytes, "UTF-8");
            T t = jsonToObject(json, tClass);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //使用阿里JSON将字符串转成对象
    public static <T> T jsonToObject(String json, Class<T> tClass) {
        T t = JSON.parseObject(json, tClass);
        return t;
    }

    public static byte[] objectToByte(java.lang.Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();


        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }

    public static Object byteToObject(byte[] bytes) {
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

}

