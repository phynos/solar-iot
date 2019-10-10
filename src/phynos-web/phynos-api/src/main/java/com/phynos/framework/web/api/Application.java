package com.phynos.framework.web.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author by Lupc
 * @date 2019/9/25.
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan({"com.phynos.framework.dao.mapper"})
@ComponentScan({"com.phynos.framework.web.api","com.phynos.framework.core"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
