package com.phynos.solar.common.oss;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/12/21 09:36
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "oss")
public class OssProperties {

    private String url;

    private String bucket;

    private String accessKey;

    private String secretKey;

}
