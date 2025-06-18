package com.phynos.solar.module.sys.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author by lupc
 * @date 2020-11-20 9:28
 */
@Setter
@Getter
public class LoginDTO {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码名不能为空")
    private String password;

}
