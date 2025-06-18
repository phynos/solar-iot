package com.solar.iot.codec;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 设备-上行-属性
 * @author lupc
 * @date 2021/3/25 10:39
 */
@Data
public class ProtoPostUl<T> {

    @JsonProperty("POST")
    private T data;

    /**
     *
     */
    private String cid;

}
