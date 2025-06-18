package com.solar.iot.model.device;

import lombok.Data;

/**
 * @author lupc
 * @date 2021/3/26 09:39
 */
@Data
public class IotAlarm {

    /**
     * 告警类型
     */
    private IotAlarmType alarmType;

    /**
     * 阈值
     */
    private String limit;



}
