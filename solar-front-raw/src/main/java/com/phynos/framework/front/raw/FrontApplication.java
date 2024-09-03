package com.phynos.framework.front.raw;

import com.phynos.framework.front.raw.netty.IotNettyServer;
import com.phynos.framework.front.raw.util.ClassUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrontApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IotNettyServer iotNettyServer;

    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ClassUtil.initTypeToMsgClassMap("com.phynos.framework.front.raw.message");
        logger.debug("启动Netty...");
        iotNettyServer.start();
    }


}
