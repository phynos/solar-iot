package com.phynos.framework.front.raw.config;

import com.phynos.framework.front.raw.netty.IotNettyChannelInitializer;
import com.phynos.framework.front.raw.netty.handler.IotNettyHeartBeatHandler;
import com.phynos.framework.front.raw.netty.handler.IotNettyLoginHandler;
import com.phynos.framework.front.raw.netty.handler.IotNettyServerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring的形式管理
 * <pre>如果需要以bean的形式使用Handler，则需要在这里进行创建，并传入</pre>
 *
 * @author lupc
 * @date 2021/10/25 15:01
 */
@Configuration
public class NettyConfig {

    @Bean
    public IotNettyChannelInitializer iotNettyChannelInitializer() {
        IotNettyChannelInitializer iotNettyChannelInitializer = new IotNettyChannelInitializer();
        //这里简单以普通形式的对象创建
        IotNettyLoginHandler iotNettyLoginHandler = new IotNettyLoginHandler();
        iotNettyChannelInitializer.addAdapter("login-handler" , iotNettyLoginHandler);
        IotNettyHeartBeatHandler iotNettyHeartBeatHandler= new IotNettyHeartBeatHandler();
        iotNettyChannelInitializer.addAdapter("heart-handler" , iotNettyHeartBeatHandler);
        IotNettyServerHandler iotNettyServerHandler = new IotNettyServerHandler();
        iotNettyChannelInitializer.addAdapter("work-handler" , iotNettyServerHandler);
        return iotNettyChannelInitializer;
    }

    @Bean
    public IotNettyFactoryBean iotNettyFactoryBean(
            NettyProperties nettyProperties,
            IotNettyChannelInitializer iotNettyChannelInitializer) {
        IotNettyFactoryBean factory = new IotNettyFactoryBean();
        factory.setNettyProperties(nettyProperties);
        factory.setIotNettyChannelInitializer(iotNettyChannelInitializer);
        return factory;
    }



}
