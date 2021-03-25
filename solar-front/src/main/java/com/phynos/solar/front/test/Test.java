package com.phynos.solar.front.test;

import com.phynos.solar.front.autoconfig.MqttV3Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @auther lupc
 * @date 2021/3/25 10:18
 */
@Slf4j
@Component
public class Test {

    @Autowired
    MqttV3Template mqttV3Template;

    @Scheduled(fixedRate = 3000)
    public void test() {
        log.debug("定时测试...");
        mqttV3Template.sendToMqtt("/UL/22R201512DB21188/property/POST","{}");
    }


}
