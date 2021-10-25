package com.phynos.framework.front.raw.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Lupc
 * @Date: 2019/10/22 19:49
 **/
@Data
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {

    private int port;

}
