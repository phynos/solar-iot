package com.solar.iot.model.device.file;

import com.solar.iot.model.device.IotDevice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("设备初始化测试")
@SpringBootTest(classes = JsonDeviceBuildTest.class)
class JsonDeviceBuildTest {

    @DisplayName("JSON协议载入测试")
    @Test
    public void aa() {
        IotDevice device = new JsonDeviceBuild("abc").fromResource("/product/temp.json").build();
        assertNotNull(device, "设备不能为空");
        assertNotNull(device.getProduct(), "设备协议不能为空");
        assertNotNull(device.getProduct().getVersion(), "协议版本不能为空");
    }


}