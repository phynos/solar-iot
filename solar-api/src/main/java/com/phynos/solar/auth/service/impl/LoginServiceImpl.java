package com.phynos.solar.auth.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.phynos.solar.auth.AuthException;
import com.phynos.solar.auth.jwttoken.dto.LoginDTO;
import com.phynos.solar.auth.vo.LoginUserVO;
import com.phynos.solar.auth.vo.TokenInfo;
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
    public LoginUserVO login(LoginDTO user) throws AuthException {
        return null;
    }

    @Override
    public TokenInfo authSuccess(HttpServletRequest request, DecodedJWT jwt) {
        //将租户id放入当前请求
        Long tenantId = Long.valueOf(jwt.getClaim("tenantId").asString());
        request.setAttribute("tenantId", tenantId);
        return null;
    }

}
