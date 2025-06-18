package com.solar.iot.model.device.file;

import com.solar.iot.model.device.DefaultIotDevice;
import com.solar.iot.model.device.IotDevice;
import com.solar.iot.model.product.DefaultIotProduct;
import com.phynos.solar.util.json.JsonUtil;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonDeviceBuild {

    private DefaultIotDevice device;

    public JsonDeviceBuild(String sn) {
        device = new DefaultIotDevice(sn);
    }

    public JsonDeviceBuild fromResource(String file) {
        try (InputStream is = getClass().getResourceAsStream(file)){
            String json = IOUtils.toString(is, StandardCharsets.UTF_8);
            DefaultIotProduct product = JsonUtil.stringToObject(json, DefaultIotProduct.class);
            device.setProduct(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public IotDevice build() {
        device.finishInit();
        return device;
    }

}
