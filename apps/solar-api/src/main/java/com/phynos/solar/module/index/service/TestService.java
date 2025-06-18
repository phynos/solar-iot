package com.phynos.solar.module.index.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/2/11 16:45
 */
@Slf4j
@Component
public class TestService {

    @Async("asyncTaskExecutor")
    public void testThread() {
        log.debug("当前处理线程名称：" + Thread.currentThread().getName());
    }

}
