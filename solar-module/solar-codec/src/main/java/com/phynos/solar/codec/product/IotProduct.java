package com.phynos.solar.codec.product;

import com.phynos.solar.codec.device.IotAtrribute;
import com.phynos.solar.codec.device.IotEvent;
import com.phynos.solar.codec.device.IotService;

import java.util.Map;

public interface IotProduct {

    Map<String, IotAtrribute> getAtrrs();

    Map<String, IotEvent> getEvents();

    Map<String, IotService> getServices();

}
