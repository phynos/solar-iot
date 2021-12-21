package com.phynos.solar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author by Lupc
 * @date 2019/9/25.
 */
@EnableAsync
@SpringBootApplication
public class SolarApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolarApiApplication.class, args);
    }

}
