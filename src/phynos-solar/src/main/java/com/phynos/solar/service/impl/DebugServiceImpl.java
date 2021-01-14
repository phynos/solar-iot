package com.phynos.solar.service.impl;

import com.phynos.solar.service.DebugService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author by lupc
 * @date 2020-12-18 14:06
 */
@Service
public class DebugServiceImpl implements DebugService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ApplicationContext ac;

    @PostConstruct
    public void init() {
        ac.getBean(DebugService.class).testThread();
    }

    @Override
    public String test() {
        ac.getBean(DebugService.class).testThread();
        return null;
    }

    @Async("asyncTaskExecutor")
    @Override
    public void testThread() {
        logger.debug("当前处理线程名称：" + Thread.currentThread().getName());
    }

}