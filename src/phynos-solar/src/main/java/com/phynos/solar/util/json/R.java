package com.phynos.solar.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by lupc
 * @date 2021-01-29 10:55
 */
@Setter
@Getter
public class R<T> {

    private int status;

    private String msg;

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

    public static R<?> codeMsg(int code, String msg) {
        R<?> r = new R<>(code, msg);
        return r;
    }

    public static R<?> codeMsg(ResultCodeEnum error, String msg) {
        R<?> r = new R<>(error.getCode(), msg);
        return r;
    }

    public static <K> R<K> data(K data) {
        R<K> r = new R<>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getMsg());
        r.setData(data);
        return r;
    }

}
