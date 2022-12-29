package com.phynos.solar.oss;

import com.phynos.solar.oss.minio.MinioOssTemplate;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/3/28 10:47
 */
@AutoConfiguration
@ConditionalOnClass(OssTemplate.class)
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {

    @Autowired
    private OssProperties ossProperties;

    @Bean
    public OssTemplate ossTemplate() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(ossProperties.getEndpoint())
                .credentials(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                .build();
        OssTemplate ossTemplate = new MinioOssTemplate(ossProperties, minioClient);
        return ossTemplate;
    }
}
