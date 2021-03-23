package com.phynos.solar.rule.easyrules.action;

import com.phynos.solar.codec.device.IotDevice;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author by lupc
 * @date 2021-02-06 11:49
 */
@Slf4j
@Setter
@Getter
public class DeviceAction implements IotAction {

    private String deviceSn;

    private String command;

    private String parameter;

    @Override
    public String execute(Map<String, IotDevice> deviceMap) {
        IotDevice device = deviceMap.get(deviceSn);
        log.debug("设备={}，参数={}，执行命令={}", device.getSn(), parameter, command);
        return null;
    }

}
