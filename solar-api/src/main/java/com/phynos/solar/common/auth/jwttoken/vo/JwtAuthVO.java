package com.phynos.solar.common.auth.jwttoken.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 返回结果封装
 *
 * @author lupc
 * @date 2021/7/3 10:03
 */
@Data
public class JwtAuthVO {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    @JsonProperty("expires_in")
    private LocalDateTime expiresIn;

    private Integer userType;

}
