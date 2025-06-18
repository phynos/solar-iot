package com.phynos.solar.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/4/11 11:30
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    AMyMVCInterceptor AMyMVCInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(AMyMVCInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //配置 （jar内置）资源文件访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        //配置 上传文件访问路径
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:static/upload/");
    }
}
