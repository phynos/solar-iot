package com.phynos.solar.auth.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.phynos.solar.auth.AuthException;
import com.phynos.solar.auth.jwttoken.dto.LoginDTO;
import com.phynos.solar.auth.vo.LoginUserVO;
import com.phynos.solar.auth.vo.TokenInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/4/26 08:52
 */
public interface UserLoginService {

    LoginUserVO login(LoginDTO user) throws AuthException;//登录接口

    TokenInfo authSuccess(HttpServletRequest request, DecodedJWT jwt);

}
