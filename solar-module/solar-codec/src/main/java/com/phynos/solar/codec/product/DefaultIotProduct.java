package com.phynos.solar.codec.product;

import com.phynos.solar.codec.device.IotAtrribute;
import com.phynos.solar.codec.device.IotEvent;
import com.phynos.solar.codec.device.IotService;

import java.util.Map;

public class DefaultIotProduct implements IotProduct {

    private String version;

    private String vendor;

    private Map<String, IotAtrribute> iotAtrrs;

    private Map<String, IotEvent> iotEvents;

    private Map<String, IotService> iotServices;

    @Override
    public Map<String, IotAtrribute> getAtrrs() {
        return iotAtrrs;
    }

    @Override
    public Map<String, IotEvent> getEvents() {
        return iotEvents;
    }

    @Override
    public Map<String, IotService> getServices() {
        return iotServices;
    }
}
