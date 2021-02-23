package com.phynos.solar.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * ajax返回结果包装类
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
     * @param error
     * @param msg
     * @return
     */
    public static R<?> msg(ResultCodeEnum error, String msg) {
        return msg(error, msg, null);
    }

    /**
     * 自定义返回msg，并附带tip
     * @param error
     * @param msg
     * @param tip
     * @return
     */
    public static R<?> msg(ResultCodeEnum error, String msg, String tip) {
        R<?> r = new R<>(error.getCode(), msg);
        r.setTip(tip);
        return r;
    }

    /**
     * 附带调试信息
     * @param error
     * @param tip 调试信息
     * @return
     */
    public static R<?> tip(ResultCodeEnum error, String tip) {
        R<?> r = code(error);
        r.setTip(tip);
        return r;
    }

    public static <K> R<K> data(K data) {
        R<K> r = new R<>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getMsg());
        r.setData(data);
        return r;
    }

}
