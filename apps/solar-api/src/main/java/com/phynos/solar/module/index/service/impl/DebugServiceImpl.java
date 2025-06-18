package com.phynos.solar.module.index.service.impl;

import com.phynos.solar.module.index.service.DebugService;
import com.phynos.solar.module.index.service.TestService;
import com.phynos.solar.module.index.vo.PersonVO;
import com.phynos.solar.util.json.R;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Cacheable(cacheNames = "test", key = "#name")
    @Override
    public List<PersonVO> testCache(String name) {
        log.info("没有命中缓存...");
        List<PersonVO> data = new ArrayList<>();
        if ("admin".equals(name)) {
            PersonVO vo = new PersonVO();
            vo.setName("admin");
            vo.setAge(18);
            vo.setImg("d");
            data.add(vo);
        }
        return data;
    }

    @CacheEvict(cacheNames = "test", key = "#name")
    @Override
    public void clearCache(String name) {

    }

}
