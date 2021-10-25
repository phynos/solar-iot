package com.phynos.framework.front.raw.config;

import com.phynos.framework.front.raw.config.NettyProperties;
import com.phynos.framework.front.raw.netty.IotNettyChannelInitializer;
import com.phynos.framework.front.raw.netty.IotNettyServer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 创建Netty Server的bean
 *
 * @author lupc
 * @date 2021/10/25 14:57
 */
public class IotNettyFactoryBean implements FactoryBean<IotNettyServer>, InitializingBean, DisposableBean {

    private IotNettyServer iotNettyServer;

    private NettyProperties nettyProperties;

    private IotNettyChannelInitializer iotNettyChannelInitializer;

    @Override
    public IotNettyServer getObject() throws Exception {
        return iotNettyServer;
    }

    @Override
    public Class<?> getObjectType() {
        return IotNettyServer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        iotNettyServer.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        iotNettyServer = new IotNettyServer(nettyProperties.getPort());
        iotNettyServer.setIotNettyChannelInitializer(iotNettyChannelInitializer);
    }

    public void setNettyProperties(NettyProperties nettyProperties) {
        this.nettyProperties = nettyProperties;
    }

    public void setIotNettyChannelInitializer(IotNettyChannelInitializer iotNettyChannelInitializer) {
        this.iotNettyChannelInitializer = iotNettyChannelInitializer;
    }
}
