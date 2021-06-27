package com.phynos.solar.common.auth;

import com.phynos.solar.util.json.JsonUtil;
import com.phynos.solar.util.json.R;
import com.phynos.solar.util.json.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("无权访问：{}", request.getRequestURI());
        R<?> r = R.code(ResultCodeEnum.NOT_HAVE_PERMISSION);
        String json = JsonUtil.objectToString(r);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

}
