package com.phynos.solar.common.auth;

import com.phynos.solar.util.json.JsonUtil;
import com.phynos.solar.util.json.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登陆成功");
        R<?> r = R.ok();
        String json = JsonUtil.objectToString(r);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

}
