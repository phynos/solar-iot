package com.phynos.solar.codec.device;

import com.phynos.solar.codec.product.DefaultIotProduct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * IOT设备
 *
 * @author by lupc
 * @date 2021-02-25 9:45
 */
@Slf4j
public class DefaultIotDevice implements IotDevice {

    private String sn;

    @Getter
    @Setter
    private DefaultIotProduct product;

    @Getter
    @Setter
    private String location;
    @Getter
    @Setter
    private String lat;

    @Getter
    @Setter
    private String lng;

    public DefaultIotDevice(String sn) {
        this.sn = sn;
    }

    @Override
    public String getSn() {
        return sn;
    }

    @Override
    public void refresh(String key, String value) {
        IotAtrribute atrribute = getAttrs().get(key);
        if(atrribute == null) {
            log.warn("无法找到属性，key={}", key);
            return;
        }
        atrribute.setValue(value);
    }

    public void calcExpr() {

    }

    @Override
    public String getVendor() {
        return product.getVendor();
    }

    @Override
    public String getVersion() {
        return product.getVersion();
    }

    @Override
    public Map<String, IotAtrribute> getAttrs() {
        return product.getAttrs();
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
