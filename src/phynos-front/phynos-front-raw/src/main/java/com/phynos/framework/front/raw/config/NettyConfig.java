package com.phynos.framework.front.raw.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Lupc
 * @Date: 2019/10/22 19:49
 **/
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyConfig {


    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
