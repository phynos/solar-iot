package com.phynos.solar.front.mqtt.simple;

import com.phynos.solar.front.mqtt.MqttProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/12/17 15:01
 */
@Configuration
@EnableConfigurationProperties(MqttProperties.class)
public class SimpleMqttConfiguration {

    private final MqttProperties mqttProperties;

    public SimpleMqttConfiguration(MqttProperties mqttProperties) {
        this.mqttProperties = mqttProperties;
    }

    @Bean
    public SimpleMqttFactoryBean simpleMqttFactoryBean() {
        SimpleMqttFactoryBean simpleMqttFactoryBean = new SimpleMqttFactoryBean();
        simpleMqttFactoryBean.setMqttProperties(mqttProperties);
        return simpleMqttFactoryBean;
    }




}
