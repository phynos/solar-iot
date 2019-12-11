package com.phynos.framework.web.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author by Lupc
 * @date 2019/9/25.
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@MapperScan({"com.phynos.framework.dao.mapper"})
@ComponentScan({"com.phynos.framework.web.api","com.phynos.framework.core"})
public class WebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApiApplication.class, args);
    }

}
