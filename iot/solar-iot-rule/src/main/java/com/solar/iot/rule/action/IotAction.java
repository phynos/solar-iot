package com.solar.iot.rule.action;

import com.solar.iot.model.device.IotDevice;

import java.util.Map;

/**
 * @author by lupc
 * @date 2021-02-18 11:48
 */
public interface IotAction {

    String execute(Map<String, IotDevice> deviceMap);

}
