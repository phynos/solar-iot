package com.phynos.solar.module.index.service.impl;

import com.phynos.solar.module.index.service.DebugService;
import com.phynos.solar.module.index.service.TestService;
import com.phynos.solar.util.json.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author by lupc
 * @date 2020-12-18 14:06
 */
@Slf4j
@Service
public class DebugServiceImpl implements ApplicationContextAware, DebugService {

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        applicationContext.getBean(TestService.class).testThread();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public R<?> test() {
        applicationContext.getBean(TestService.class).testThread();
        return R.ok();
    }


}
