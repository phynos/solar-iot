package com.solar.iot.rule;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 规则 fire类型
 *
 * @author by lupc
 * @date 2021-02-06 11:41
 */
public enum RuleFireType {

    @JsonProperty("数据驱动")
    数据驱动,
    @JsonProperty("定时驱动")
    定时驱动

}
