package com.phynos.solar.rule.easyrules.device;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 设备类
 * @author by lupc
 * @date 2021-02-06 13:38
 */
@Setter
@Getter
public class IotDevice {

    /**
     * 设备序列号
     */
    private String sn;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备点表
     */
    private Map<String, IotSignal> signals;

    public IotDevice() {
        signals = new HashMap<>();
    }

}
