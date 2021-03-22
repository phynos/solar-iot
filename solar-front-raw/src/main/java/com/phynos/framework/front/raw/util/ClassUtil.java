package com.phynos.framework.front.raw.util;

import com.phynos.framework.front.raw.annotation.IotMsgType;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author by Lupc
 * @date 2020/4/4.
 */
public class ClassUtil {

    private static Map<Integer, Class<?>> typeToMsgClassMap;

    // 根据类型得到对应的消息类的class对象
    public static Class<?> getMsgClassByType(int type) {
        return typeToMsgClassMap.get(type);
    }

    /**
     * 初始化typeToMsgClassMap
     * 取得消息类的class文件
     *
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void initTypeToMsgClassMap(String pack)
            throws ClassNotFoundException, IOException {

        Map<Integer, Class<?>> tmpMap = new HashMap<>();


        Set<Class<?>> classSet = ClassTools.getClasses(pack);
        if (classSet != null) {
            for (Class<?> clazz : classSet) {
                if (clazz.isAnnotationPresent(IotMsgType.class)) {
                    IotMsgType annotation = clazz.getAnnotation(IotMsgType.class);
                    tmpMap.put(annotation.msgType(), clazz);
                }
            }
        }

        typeToMsgClassMap = Collections.unmodifiableMap(tmpMap);
    }

}
