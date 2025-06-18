package com.solar.iot.model.device;

/**
 * 告警类型
 *
 * @author lupc
 * @date 2021/3/26 09:38
 */
public enum IotAlarmType {

    /**
     * 值改变告警
     */
    值变告警,
    /**
     * 阈值判断告警
     */
    阈值告警,
    /**
     * 组合式告警
     */
    规则告警,
    /**
     * 下位机主动上报
     */
    事件告警

}
