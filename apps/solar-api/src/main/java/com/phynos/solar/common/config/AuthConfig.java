package com.phynos.solar.common.config;

import com.phynos.solar.auth.service.UserLoginService;
import com.phynos.solar.auth.service.impl.UserLoginServiceImpl;
import com.phynos.solar.module.ten.service.TenUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/11/3 15:52
 */
@Configuration
public class AuthConfig {

    @Bean
    public UserLoginService userLoginService(TenUserService tenUserService) {
        UserLoginService userLoginService = new UserLoginServiceImpl(tenUserService);
        return userLoginService;
    }

}
