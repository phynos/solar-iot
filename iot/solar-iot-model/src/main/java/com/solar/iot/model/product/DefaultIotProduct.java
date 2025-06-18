package com.solar.iot.model.product;

import com.solar.iot.model.device.IotAtrribute;
import com.solar.iot.model.device.IotEvent;
import com.solar.iot.model.device.IotService;
import lombok.Setter;

import java.util.Map;

@Setter
public class DefaultIotProduct implements IotProduct {

    private String code;

    private String version;

    private String vendor;

    private Map<String, IotAtrribute> attrs;

    private Map<String, IotEvent> events;

    private Map<String, IotService> services;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getVendor() {
        return vendor;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public Map<String, IotAtrribute> getAttrs() {
        return attrs;
    }

    @Override
    public Map<String, IotEvent> getEvents() {
        return events;
    }

    @Override
    public Map<String, IotService> getServices() {
        return services;
    }

}
