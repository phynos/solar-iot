package com.phynos.solar.auth.jwttoken.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/7/1 15:48
 */
@Data
public class LoginDTO {

    @NotNull(message = "必须设定用户名")
    private String username;

    @NotNull(message = "必须设定密码")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,20}$", message = "密码必须是4-20位英文字符、下划线和数字组成")
    private String password;

    private String code;

}
