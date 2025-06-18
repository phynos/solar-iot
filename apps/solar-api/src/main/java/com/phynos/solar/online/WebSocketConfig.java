package com.phynos.solar.online;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Lupc
 * @Date: 2019/12/4 11:59
 **/
@Configuration
public class WebSocketConfig {

    @ConditionalOnWebApplication
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
