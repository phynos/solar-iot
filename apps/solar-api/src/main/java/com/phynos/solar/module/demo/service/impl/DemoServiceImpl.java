package com.phynos.solar.module.demo.service.impl;

import com.phynos.solar.module.demo.service.DemoService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/6/20 16:59
 */
@Service
public class DemoServiceImpl implements DemoService {


    @Profile("lupc")
    @PostConstruct
    public void test() {
        //loginService.testStreamQuery();
        //loginService.testBatchInsert();
    }


}
