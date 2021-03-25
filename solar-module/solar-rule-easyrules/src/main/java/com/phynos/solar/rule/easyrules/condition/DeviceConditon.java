package com.phynos.solar.rule.easyrules.condition;

import com.phynos.solar.codec.device.IotAtrribute;
import com.phynos.solar.codec.device.IotDevice;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

/**
 * @author by lupc
 * @date 2021-02-06 11:51
 */
@Slf4j
@Setter
@Getter
public class DeviceConditon implements IotCondition {

    private String deviceSn;

    private String signalKey;

    private OperType operType;

    private String limit;

    @Override
    public boolean evaluate(Map<String, IotDevice> deviceMap) {
        IotDevice device = deviceMap.get(deviceSn);
        final IotAtrribute signal = device.getAttrs().get(signalKey);
        double value = NumberUtils.toDouble(signal.getValue());
        double limitValue = NumberUtils.toDouble(limit, 0);
        log.debug("当前值={}，限制值={}，操作={}", value, limit, operType.name());
        switch (operType) {
            case 大于:
                return value > limitValue;
            case 等于:
                return value == limitValue;
            case 小于:
                return value < limitValue;
            default:
                throw new RuntimeException("不支持的操作符");
        }
    }
}
