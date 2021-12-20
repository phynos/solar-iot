package com.phynos.solar.front.mqtt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * mqtt代理地址
 *
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
