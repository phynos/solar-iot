package com.phynos.solar.auth.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.phynos.solar.auth.TokenInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/4/26 08:52
 */
public interface LoginService {

    TokenInfo authSuccess(HttpServletRequest request, DecodedJWT jwt);

}
