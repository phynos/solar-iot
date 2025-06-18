package com.phynos.solar.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.phynos.solar.auth.jwttoken.util.Auth0JwtUtil;
import com.phynos.solar.auth.jwttoken.util.BearerTokenUtil;
import com.phynos.solar.auth.service.UserLoginService;
import com.phynos.solar.auth.vo.TokenInfo;
import com.phynos.solar.util.json.JsonUtil;
import com.phynos.solar.util.json.R;
import com.phynos.solar.util.json.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * token登录拦截器
 *
 * @author lupc
 * @date 2020-12-18 11:23
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    AuthProperties authProperties;
    @Autowired
    UserLoginService userLoginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //排除健康检查请求,使日志更加清晰
        String uri = request.getRequestURI();
        log.info("进入拦截器：{}", uri);
        String authHeader = request.getHeader(BearerTokenUtil.TOKEN_HEADER);
        final String authToken = BearerTokenUtil.getAuthToken(authHeader);
        if (authToken == null) {
            response(response);
            return false;
        }
        DecodedJWT jwt = Auth0JwtUtil.verify(authProperties.getSecret(), authToken);
        if (jwt == null) {
            response(response);
            return false;
        } else {
            TokenInfo tokenInfo = userLoginService.authSuccess(request, jwt);
            log.info("当前用户：{}", tokenInfo.getUsername());
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("postHandle-token");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("afterCompletion-token");
    }

    private void response(HttpServletResponse response) throws IOException {
        R<?> r = R.code(ResultCodeEnum.TOKEN_INVALID);
        String json = JsonUtil.objectToString(r);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }

}
