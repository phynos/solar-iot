package com.phynos.framework.front.raw.service.impl;

import com.phynos.framework.front.raw.service.RmiClientService;
import com.phynos.framework.front.raw.service.RmiServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Service;

/**
 * @author by lupc
 * @date 2020-04-06 9:57
 */
@Service
public class RmiClientServiceImpl implements RmiClientService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("rmiServer")
    RmiServiceExporter rmiServiceExporter;

    @Autowired
    @Qualifier("rmiClient")
    RmiProxyFactoryBean factoryBean;

    @Override
    public String sayHello(String msg) throws Exception {
        logger.debug(rmiServiceExporter.toString());
        logger.debug("recieve:{}" , msg);
        RmiServerService service = (RmiServerService) factoryBean.getObject();
        return service.sayHello(msg);
    }

}
