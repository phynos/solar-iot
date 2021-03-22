package com.phynos.solar.codec.device;

import com.phynos.solar.codec.product.DefaultIotProduct;
import lombok.Getter;

import java.util.Map;

/**
 * IOT设备
 *
 * @author by lupc
 * @date 2021-02-25 9:45
 */
@Getter
public class DefaultIotDevice implements IotDevice {

    private String sn;

    private DefaultIotProduct product;

    private String location;

    @Override
    public Map<String, IotAtrribute> getAtrrs() {
        return product.getAtrrs();
    }

    @Override
    public Map<String, IotEvent> getEvents() {
        return product.getEvents();
    }

    @Override
    public Map<String, IotService> getServices() {
        return product.getServices();
    }

}
