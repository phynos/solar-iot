package com.phynos.solar.auth.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.phynos.solar.auth.AuthException;
import com.phynos.solar.auth.jwttoken.dto.LoginDTO;
import com.phynos.solar.auth.service.UserLoginService;
import com.phynos.solar.auth.user.UserDetailService;
import com.phynos.solar.auth.user.UserDetails;
import com.phynos.solar.auth.vo.TokenInfo;
import com.phynos.solar.util.json.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/4/26 08:56
 */
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {

    private final UserDetailService userDetailService;

    public UserLoginServiceImpl(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public TokenInfo login(LoginDTO dto) throws AuthException {
        UserDetails userDetails = userDetailService.loadUserByUsername(dto.getUsername());
        //检查用户是否存在
        if (userDetails == null) {
            throw new AuthException(ResultCodeEnum.USERNAME_PASSWORD_MISMATCH);
        }
        //检查用户是否被禁用
        if (!userDetails.isEnabled()) {
            throw new AuthException(ResultCodeEnum.USER_DISABLED);
        }
        HttpServletRequest request = getHttpServletRequest();
        return loginSuccess(request, userDetails);
    }

    @Override
    public TokenInfo authSuccess(HttpServletRequest request, DecodedJWT jwt) {
        //将租户id放入当前请求
        Long tenantId = Long.valueOf(jwt.getClaim("tenantId").asString());
        request.setAttribute("tenantId", tenantId);
        TokenInfo tokenInfo = TokenInfo.fromJWT(jwt);
        request.setAttribute("tokenInfo", tokenInfo);
        return tokenInfo;
    }

    public TokenInfo loginSuccess(HttpServletRequest request, UserDetails userDetails) {
        request.setAttribute("tenantId", userDetails.getTenantId());
        return TokenInfo.fromUserDetails(userDetails);
    }

    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request;
    }

}
