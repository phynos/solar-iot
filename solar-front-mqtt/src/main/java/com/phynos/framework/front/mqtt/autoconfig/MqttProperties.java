package com.phynos.framework.front.mqtt.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author by lupc
 * @date 2021-02-03 11:46
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    private String clientId;

    private String url;

    private String username;

    private String password;

    private String defaultTopic;

}
