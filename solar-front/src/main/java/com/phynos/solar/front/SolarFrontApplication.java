package com.phynos.solar.front;


import com.phynos.solar.front.module.tenancy.IotTenancy;
import com.phynos.solar.front.module.tenancy.service.TenancyService;
import com.phynos.solar.front.mqtt.MqttProperties;
import com.phynos.solar.front.mqtt.simple.SimpleMqttClient;
import com.solar.iot.model.device.file.JsonDeviceBuild;
import com.solar.iot.rule.easyrules.rule.IotRuleBuild;
import com.solar.iot.rule.easyrules.rule.IotRuleImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

/**
 * @author by lupc
 * @date 2020-09-29 15:12
 */
@EnableConfigurationProperties(MqttProperties.class)
@Slf4j
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SolarFrontApplication implements CommandLineRunner {

    SimpleMqttClient simpleMqttClient;
    @Autowired
    MqttProperties mqttProperties;

    public static void main(String[] args) {
        SpringApplication.run(SolarFrontApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        simpleMqttClient = new SimpleMqttClient(mqttProperties);
        simpleMqttClient.connect();
        simpleMqttClient.subscriber();
        simpleMqttClient.publisher();
        System.in.read();
    }

    @Autowired
    TenancyService tenancyService;

    @PostConstruct
    public void test() {
        IotTenancy tenancy = new IotTenancy("te-001");
        initDevice(tenancy);
        initRule(tenancy);
        tenancyService.add(tenancy);
    }

    private void initDevice(IotTenancy tenancy) {
        log.debug("初始化设备列表...");
        //从json文件初始化-正式环境从数据库初始化
        tenancy.addDevice(
                new JsonDeviceBuild("22R201512DB21188").fromResource("/product/temp.json").build(),
                new JsonDeviceBuild("0102").fromResource("/product/box.json").build()
        );
    }

    private void initRule(IotTenancy tenancy) {
        log.debug("初始化规则引擎...");
        //从json文件初始化-正式环境从数据库初始化
        IotRuleImpl openRule = new IotRuleBuild().fronJson("/ruler/open.json").build();
        IotRuleImpl closeRule = new IotRuleBuild().fronJson("/ruler/open.json").build();
        tenancy.addRule(openRule, closeRule);
    }

}
