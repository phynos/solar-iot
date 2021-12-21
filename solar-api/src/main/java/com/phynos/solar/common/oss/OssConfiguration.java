package com.phynos.solar.common.oss;

import com.phynos.solar.common.oss.minio.MinioTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/12/21 10:50
 */
@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssConfiguration {

    private final OssProperties ossProperties;

    public OssConfiguration(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    @Bean
    public OssTemplate ossTemplate() {
        MinioTemplate minioTemplate = new MinioTemplate(ossProperties);
        return minioTemplate;
    }

}
