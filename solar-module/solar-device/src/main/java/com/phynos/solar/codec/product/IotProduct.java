package com.phynos.solar.codec.product;

import com.phynos.solar.codec.device.IotAtrribute;
import com.phynos.solar.codec.device.IotEvent;
import com.phynos.solar.codec.device.IotService;

import java.util.Map;

public interface IotProduct {

    String getCode();

    String getVendor();

    String getVersion();

    Map<String, IotAtrribute> getAttrs();

    Map<String, IotEvent> getEvents();

    Map<String, IotService> getServices();

}
