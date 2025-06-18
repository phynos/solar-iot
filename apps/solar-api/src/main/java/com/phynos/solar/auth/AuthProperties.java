package com.phynos.solar.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/4/26 08:36
 */
@Data
@Component
@ConfigurationProperties(prefix = "auth", ignoreUnknownFields = false)
public class AuthProperties {

    /**
     * 秘钥
     */
    private String secret;

    /**
     * 超时天数
     */
    private Integer expires;

    private Boolean loginKaptcha = false;

    private Boolean registerKaptcha = true;

    private String superPassword;


    /**
     * 不需要认证可以访问的URI
     */
    private String[] excludePath;

}
