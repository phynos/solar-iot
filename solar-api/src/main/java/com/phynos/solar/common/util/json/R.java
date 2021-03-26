package com.phynos.solar.common.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * ajax返回结果包装类
 *
 * @author by lupc
 * @date 2021-01-29 10:55
 */
@Setter
@Getter
public class R<T> {

    /**
     * 状态值
     */
    private int status;

    /**
     * 返回消息，可直接显示给用户看
     */
    private String msg;

    /**
     * 这个字段用来开发阶段传递一些错误信息给前端，方便调试（不必定义过多的错误码）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tip;

    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public R(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static R<?> ok() {
        return code(ResultCodeEnum.OK);
    }

    public static R<?> code(ResultCodeEnum error) {
        R<?> r = new R<>(error.getCode(), error.getMsg());
        return r;
    }

    /**
     * 自定义返回msg
     *
     * @param error
     * @param msg
     * @return
     */
    public static R<?> msg(ResultCodeEnum error, String msg) {
        R<?> r = new R<>(error.getCode(), msg);
        return r;
    }

    /**
     * 自定义返回msg，支持格式化
     *
     * @param error
     * @param format    msg的format
     * @param arguments
     * @return
     */
    public static R<?> msg(ResultCodeEnum error, String format, Object... arguments) {
        String msg = String.format(format, arguments);
        return msg(error, msg);
    }

    /**
     * 附带调试信息
     *
     * @param error
     * @param tip   调试信息
     * @return
     */
    public static R<?> tip(ResultCodeEnum error, String tip) {
        R<?> r = code(error);
        r.setTip(tip);
        return r;
    }

    /**
     * 附带调试信息，支持格式化
     * @param error
     * @param format
     * @param arguments
     * @return
     */
    public static R<?> tip(ResultCodeEnum error, String format, Object... arguments) {
        String tip = String.format(format, arguments);
        return tip(error, tip);
    }

    public static <K> R<K> data(K data) {
        R<K> r = new R<>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getMsg());
        r.setData(data);
        return r;
    }

    /**
     * 生成简易键值对数据返回结果
     */
    public static class Build {

        private final Map<String,Object> data = new HashMap<>();

        public Build() {

        }

        public Build put(String key,Object value) {
            data.put(key, value);
            return this;
        }

        public R<?> build() {
            return R.data(data);
        }

    }

}
