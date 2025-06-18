package com.solar.iot.codec;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 服务器-下行-属性-查询
 *
 * @author lupc
 * @date 2021/3/25 10:58
 */
@Data
public class ProtoGetDl<T, P> {

    @JsonProperty("GET")
    private T data;

    @JsonProperty("params")
    private P params;

    /**
     * 交互id，由发起方维护，最小值=1
     */
    private Integer cid;

}
