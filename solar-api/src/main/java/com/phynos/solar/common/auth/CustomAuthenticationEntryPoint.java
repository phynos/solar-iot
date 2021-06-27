package com.phynos.solar.common.auth;

import com.phynos.solar.util.json.JsonUtil;
import com.phynos.solar.util.json.R;
import com.phynos.solar.util.json.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("无登陆访问：{}", request.getRequestURI());
        R<?> r = R.code(ResultCodeEnum.LOGIN_FIRST);
        String json = JsonUtil.objectToString(r);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

}
