package com.phynos.framework.front.mqtt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by lupc
 * @date 2021-02-03 11:51
 */
@Configuration
@EnableConfigurationProperties(MqttProperties.class)
public class MqttAutoConfiguration {

    private MqttProperties mqttProperties;

    public MqttAutoConfiguration(MqttProperties mqttProperties) {
        this.mqttProperties = mqttProperties;
    }

    @Bean
    public SimpleMqttClient simpleMqttClient() {
        SimpleMqttClient simpleMqttClient = new SimpleMqttClient(mqttProperties);
        return simpleMqttClient;
    }

}
