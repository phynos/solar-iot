package com.phynos.framework.front.raw;

import com.phynos.framework.front.raw.distributed.IotNettyRemoteManager;
import com.phynos.framework.front.raw.distributed.IotWorker;
import com.phynos.framework.front.raw.netty.IotNettyServer;
import com.phynos.framework.front.raw.util.ClassUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrontNettyApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IotNettyServer iotNettyServer;

    public static void main(String[] args) {
        SpringApplication.run(FrontNettyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ClassUtil.initTypeToMsgClassMap("com.phynos.framework.front.raw.message");
        if (System.currentTimeMillis() < 1) {
            logger.debug("初始化节点信息...");
            IotWorker.getInst().setLocalNode("127.0.0.1", 9600);
            logger.debug("启动节点...");
            IotWorker.getInst().init();
            logger.debug("启动节点的管理...");
            IotNettyRemoteManager.getInst().init();
        }
        logger.debug("启动Netty...");
        iotNettyServer.start();
    }



}
