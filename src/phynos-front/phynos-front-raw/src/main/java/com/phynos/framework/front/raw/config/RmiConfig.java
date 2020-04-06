package com.phynos.framework.front.raw.config;

import com.phynos.framework.front.raw.service.RmiServerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import javax.annotation.Resource;

/**
 * @author by lupc
 * @date 2020-04-06 9:31
 */
@Configuration
public class RmiConfig {

    @Resource
    RmiServerService rmiServerService;

    @Bean(name = "rmiServer")
    public RmiServiceExporter rmiServiceExporter(){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("rmiService");
        rmiServiceExporter.setService(rmiServerService);
        rmiServiceExporter.setServiceInterface(RmiServerService.class);
        rmiServiceExporter.setRegistryPort(1099);// 默认为1099，注意占用问题
        return rmiServiceExporter;
    }

    @Bean(name = "rmiClient")
    @DependsOn({"rmiServer"})
    public RmiProxyFactoryBean initRmiProxyFactoryBean() {
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://172.16.22.89:1099/rmiService");
        factoryBean.setServiceInterface(RmiServerService.class);
        return factoryBean;
    }

}
