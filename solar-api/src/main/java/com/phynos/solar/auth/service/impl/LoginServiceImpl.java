package com.phynos.solar.auth.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.phynos.solar.auth.TokenInfo;
import com.phynos.solar.auth.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/4/26 08:56
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public TokenInfo authSuccess(HttpServletRequest request, DecodedJWT jwt) {
        return null;
    }

}
