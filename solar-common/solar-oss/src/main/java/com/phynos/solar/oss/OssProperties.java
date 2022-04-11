package com.phynos.solar.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/3/28 10:57
 */
@ConfigurationProperties(prefix = "minio", ignoreUnknownFields = false)
@Data
@Component
public class OssProperties {

    /**
     * 服务地址
     */
    private String endpoint;

    /**
     * 存储桶名称
     */
    private String bucketName;

    /**
     * 用户名
     */
    private String accessKey;

    /**
     * 密码
     */
    private String secretKey;

}
