package com.phynos.solar.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 加载一些启动后执行的任务
 */
@Order(2)
@Component
public class AppStart {

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void test(){
        logger.debug("phynos-api start");
    }

}
