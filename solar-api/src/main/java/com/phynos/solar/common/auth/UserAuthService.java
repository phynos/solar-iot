package com.phynos.solar.common.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户认证相关的接口
 *
 * @author lupc
 * @date 2021/7/3 14:26
 */
@Component
public class UserAuthService {


    public CustomUserDetails getLoginUser(String username) {
        String password = "";//从数据库拿用户
        //定义权限列表.
        List<GrantedAuthority> authorities = new ArrayList<>();
        CustomUserDetails userDetails = new CustomUserDetails(
                username,
                password,
                authorities);
        return userDetails;
    }

    //登录的时候，创建登录令牌
    public UsernamePasswordAuthenticationToken
    createUpAuthTokenBeforAuth(
            String username,
            String password) {
        //这里必须是原始密码
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        return upToken;
    }

    //根据jwt创建 登录令牌
    public UsernamePasswordAuthenticationToken
    createUpAuthTokenByUs(CustomUserDetails userDetails) {
        //登录之后，只是把用户标记位写入上下文，不再需要进行springsecurity的认证操作
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                userDetails, //登录后，传入用户信息，方便后续操作
                null, //这里其实已经不需要密码了，也无法传入原始密码
                userDetails.getAuthorities());
        return upToken;
    }

}
