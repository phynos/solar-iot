package com.phynos.solar.front.module.device;

import com.fasterxml.jackson.core.type.TypeReference;
import com.phynos.solar.codec.ProtoPostUl;
import com.phynos.solar.codec.device.IotDevice;
import com.phynos.solar.codec.device.file.JsonDeviceBuild;
import com.phynos.solar.rule.easyrules.rule.IotRule;
import com.phynos.solar.rule.easyrules.rule.IotRuleBuild;
import com.phynos.solar.util.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lupc
 * @date 2021/3/25 14:35
 */
@Slf4j
@Component("deviceService")
public class DeviceService {

    @Autowired
    ApplicationContext ac;

    /**
     * 设备列表
     */
    final Map<String, IotDevice> deviceMap = new HashMap<>();

    RulesEngine rulesEngine = new DefaultRulesEngine();
    Facts facts = new Facts();
    Rules rules = new Rules();

    @PostConstruct
    public void init() {
        //1.初始化设备上下文
        initDevice();
        //2.初始化规则引擎
        initRule();
    }

    public void initDevice() {
        log.debug("初始化设备列表...");
        //从json文件初始化-正式环境从数据库初始化
        IotDevice device = new JsonDeviceBuild("22R201512DB21188").fromResource("/product/temp.json").build();
        deviceMap.put(device.getSn(), device);
        device = new JsonDeviceBuild("0102").fromResource("/product/box.json").build();
        deviceMap.put(device.getSn(), device);
    }

    public void initRule() {
        log.debug("初始化规则引擎...");
        //从json文件初始化-正式环境从数据库初始化
        IotRule openRule = new IotRuleBuild().fronJson("/ruler/open.json").build();
        IotRule closeRule = new IotRuleBuild().fronJson("/ruler/open.json").build();
        //注册规则
        rules.register(openRule, closeRule);
        //准备facts
        facts.put("deviceMap", deviceMap);
    }

    public void data(Message<?> message) {
        String topic = message.getHeaders().get("mqtt_receivedTopic", String.class);
        Integer qos = message.getHeaders().get("mqtt_receivedQos", Integer.class);
        //message.getHeaders().keySet().forEach(key -> log.debug("{}", key));
        data(topic, message.getPayload().toString(), qos);
    }

    public void data(String topic, String payload, Integer qos) {
        log.debug("[mqtt]设备数据接收：topic={},qos={},payload={}", topic, qos, payload);
        String[] t = StringUtils.split(topic, "/");
        String sn = t[1];
        DeviceService ds = ac.getBean(DeviceService.class);
        ds.handleData(sn, payload);
    }

    @Async("dataHandlerExecutor")
    public void handleData(String sn, String json) {
        log.debug("[线程={}]序列号={}", Thread.currentThread().getName(), sn);
        IotDevice device = deviceMap.get(sn);
        if (device == null) {
            return;
        }
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
        rulesEngine.fire(rules, facts);
    }

    private IotDevice createDevice(String sn, String product) {
        IotDevice device = new JsonDeviceBuild(sn).fromResource(product).build();
        return device;
    }

}
