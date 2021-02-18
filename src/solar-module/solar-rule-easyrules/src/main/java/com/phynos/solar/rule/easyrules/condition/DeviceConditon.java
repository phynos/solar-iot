package com.phynos.solar.rule.easyrules.condition;

import com.phynos.solar.rule.easyrules.device.IotDevice;
import com.phynos.solar.rule.easyrules.device.IotSignal;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

/**
 * @author by lupc
 * @date 2021-02-06 11:51
 */
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
        final IotSignal signal = device.getSignals().get(signalKey);
        switch (operType) {
            case 大于:
                return signal.getVal() > NumberUtils.toDouble(limit, 0);
            case 等于:
                return signal.getVal() == NumberUtils.toDouble(limit, 0);
            case 小于:
                return signal.getVal() < NumberUtils.toDouble(limit, 0);
        }
        return false;
    }
}
