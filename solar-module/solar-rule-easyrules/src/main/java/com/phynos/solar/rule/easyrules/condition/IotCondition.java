package com.phynos.solar.rule.easyrules.condition;

import com.phynos.solar.rule.easyrules.device.IotDevice;

import java.util.Map;

/**
 * @author by lupc
 * @date 2021-02-18 11:30
 */
public interface IotCondition {

    boolean evaluate(Map<String, IotDevice> deviceMap);

}
