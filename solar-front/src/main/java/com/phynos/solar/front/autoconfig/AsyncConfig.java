package com.phynos.solar.front.autoconfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池的配置
 * @author by lupc
 * @date 2020-08-07 13:54
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    private static final int MAX_POOL_SIZE = 10;

    private static final int CORE_POOL_SIZE = 5;

    @Bean("dataHandlerExecutor")
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        asyncTaskExecutor.setThreadNamePrefix("device-handle-");
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }

}
