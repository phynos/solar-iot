package com.phynos.solar.front.module.device.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.phynos.solar.codec.ProtoPostUl;
import com.phynos.solar.codec.device.IotDevice;
import com.phynos.solar.front.module.device.DeviceService;
import com.phynos.solar.front.module.tenancy.IotTenancy;
import com.phynos.solar.front.module.tenancy.service.TenancyService;
import com.phynos.solar.util.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lupc
 * @date 2021/3/25 14:35
 */
@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    ApplicationContext ac;

    @Autowired
    TenancyService tenancyService;

    @Override
    public void data(Message<?> message) {
        String topic = message.getHeaders().get("mqtt_receivedTopic", String.class);
        Integer qos = message.getHeaders().get("mqtt_receivedQos", Integer.class);
        //message.getHeaders().keySet().forEach(key -> log.debug("{}", key));
        data(topic, message.getPayload().toString(), qos);
    }

    @Override
    public void data(String topic, String payload, Integer qos) {
        log.debug("[mqtt]设备数据接收：topic={},qos={},payload={}", topic, qos, payload);
        String[] t = StringUtils.split(topic, "/");
        String sn = t[1];
        DeviceService ds = ac.getBean(DeviceService.class);
        ds.handleData(sn, payload);
    }

    @Async("dataHandlerExecutor")
    @Override
    public void handleData(String sn, String json) {
        String tenancyId = "te-001";//
        IotTenancy tenancy = tenancyService.get(tenancyId);

        log.debug("[线程={}]序列号={}", Thread.currentThread().getName(), sn);
        IotDevice device = tenancy.getDevice(sn);
        //解码
        ProtoPostUl<Map<String, Object>> postData = JsonUtil.stringToObject(json, new TypeReference<ProtoPostUl<Map<String, Object>>>() {
        });
        assert postData != null;
        Map<String, Object> properties = postData.getData();
        properties.forEach((k, v) -> {
            //更新数据
            log.debug("k={},v={}", k, v);
            device.refresh(k, v.toString());
        });
        //规则引擎执行
        tenancy.fireRules();
    }

    @Override
    public void saveData2Db() {

    }

}
