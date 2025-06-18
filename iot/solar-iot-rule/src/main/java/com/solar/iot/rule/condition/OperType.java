package com.solar.iot.rule.condition;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 单个条件的算术符
 *
 * @author by lupc
 * @date 2021-02-06 11:53
 */
public enum OperType {

    @JsonProperty("大于")
    大于,
    @JsonProperty("等于")
    等于,
    @JsonProperty("小于")
    小于

}
