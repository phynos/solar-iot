package com.solar.iot.codec;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 服务器-下行-属性-设置
 *
 * @author lupc
 * @date 2021/3/25 11:17
 */
@Data
public class ProtoSetDl<T, P> {

    @JsonProperty("SET")
    private T data;

    @JsonProperty("params")
    private P params;

    /**
     * 设置模式-0:设置并保存（默认值）1:设置不保存(临时生效)
     */
    private int mode;

    /**
     * 交互id，由发起方维护，最小值=1
     */
    private Integer cid;

}
