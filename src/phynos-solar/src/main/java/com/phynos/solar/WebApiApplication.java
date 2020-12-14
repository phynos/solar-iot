package com.phynos.solar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author by Lupc
 * @date 2019/9/25.
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
@MapperScan({"com.phynos.solar.mapper"})
public class WebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApiApplication.class, args);
    }

}
