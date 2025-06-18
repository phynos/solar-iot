package com.solar.iot.rule.action;

import com.solar.iot.model.device.IotDevice;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * IOT规则-执行
 *
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
        if(device == null) {
            log.warn("IOT规则-规则执行无法找到设备，sn={}", deviceSn);
            return null;
        }
        log.debug("设备={}，参数={}，执行命令={}", device.getSn(), parameter, command);
        return null;
    }

}
