package com.phynos.solar.common.auth;

import com.phynos.solar.common.auth.usersource.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/12/14 09:16
 */
@Configuration
public class AuthBeanFactory {

    /**
     * 指定加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService(PasswordEncoder passwordEncoder) {
        CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService(passwordEncoder);
        return customUserDetailsService;
    }

}
