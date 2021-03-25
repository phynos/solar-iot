package com.phynos.solar.front.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.phynos.solar.codec.ProtoPostUl;
import com.phynos.solar.front.autoconfig.MqttV3Template;
import com.phynos.solar.util.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @auther lupc
 * @date 2021/3/25 10:18
 */
@Slf4j
@Component
public class Test {

    @Autowired
    MqttV3Template mqttV3Template;

    ProtoPostUl<Map<String, Object>> payload;

    @PostConstruct
    public void init() {
        try (InputStream is = getClass().getResourceAsStream("/packet/temp.json")) {
            String json = IOUtils.toString(is, StandardCharsets.UTF_8);
            payload = JsonUtil.stringToObject(json, new TypeReference<ProtoPostUl<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 3000)
    public void test() {
        log.debug("[mqtt]设备数据上报...");
        payload.getData().put("temp", RandomUtils.nextInt(0, 50) + "");
        String json = JsonUtil.objectToString(payload);
        mqttV3Template.sendToMqtt("/UL/22R201512DB21188/property/POST", json);
    }


}
