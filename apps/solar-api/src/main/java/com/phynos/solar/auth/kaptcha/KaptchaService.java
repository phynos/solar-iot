package com.phynos.solar.auth.kaptcha;

import com.phynos.solar.util.json.ResultCodeEnum;

import jakarta.servlet.http.HttpServletResponse;

public interface KaptchaService {

    void kaptcha(HttpServletResponse response);

    ResultCodeEnum valid(String code);

}
