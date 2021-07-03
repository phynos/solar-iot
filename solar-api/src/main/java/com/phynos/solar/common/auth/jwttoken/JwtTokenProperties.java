package com.phynos.solar.common.auth.jwttoken;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT 配置
 *
 * @author lupc
 * @date 2021/7/3 09:49
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = false)
public class JwtTokenProperties {

    /**
     * 秘钥
     */
    private String secret;

    /**
     * 超时天数
     */
    private Integer expires;


}
