package com.solar.iot.codec;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 服务器-上行-属性-查询-回复
 *
 * @author lupc
 * @date 2021/3/25 11:00
 */
@Data
public class ProtoGetUlResp<T> extends ProtoResp {

    @JsonProperty("GET")
    private T get;

}
