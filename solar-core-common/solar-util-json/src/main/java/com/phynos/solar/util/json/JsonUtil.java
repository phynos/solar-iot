package com.phynos.solar.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * JSON序列化工具类
 *
 * @author by lupc
 * @date 2020-04-10 10:47
 */
public class JsonUtil {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    static {
        //允许 json字符串有未知的属性
        JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
    }

    /**
     * 将一个对象转换 序列化为json字符串
     *
     * @param object
     * @return
     */
    public static String objectToString(Object object) {
        try {
            return JSON_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将一个字符串 反序列化为一个对象
     *
     * @param json
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T stringToObject(String json, Class<T> valueType) {
        try {
            return JSON_MAPPER.readValue(json, valueType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将一个字符串 反序列化为一个对象
     * @param json
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static <T> T stringToObject(String json, TypeReference<T> valueTypeRef) {
        try {
            return JSON_MAPPER.readValue(json, valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        try {
            return JSON_MAPPER.readValue(json, getCollectionType(List.class, cls));
        } catch (JsonProcessingException e) {
        }
        return null;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  实体bean
     * @return JavaType Java类型
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return JSON_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
