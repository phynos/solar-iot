package com.phynos.framework.web.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Lupc
 * @Date: 2019/10/18 20:01
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FormContentFilter formContentFilter() {
        return new FormContentFilter();
    }

}
