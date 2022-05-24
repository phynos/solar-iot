package com.phynos.solar.auth;

import com.phynos.solar.auth.jwttoken.dto.LoginDTO;
import com.phynos.solar.auth.jwttoken.util.Auth0JwtUtil;
import com.phynos.solar.auth.jwttoken.vo.JwtAuthVO;
import com.phynos.solar.auth.kaptcha.KaptchaService;
import com.phynos.solar.auth.service.LoginService;
import com.phynos.solar.auth.vo.LoginUserVO;
import com.phynos.solar.util.json.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证接口
 *
 * @author lupc
 * @date 2021/7/1 19:29
 */
@RestController
@RequestMapping("/auth/token")
public class LoginController {

    @Autowired
    AuthProperties authProperties;
    @Autowired
    KaptchaService kaptchaService;
    @Autowired
    LoginService loginService;

    @PostMapping("")
    public R<JwtAuthVO> tokenAuth(@RequestBody @Valid LoginDTO dto) throws AuthException {
        LoginUserVO loginUserVO = loginService.login(dto);
        //
        Map<String, String> map = new HashMap<>();//用来存放payload
        map.put("id", String.valueOf(loginUserVO.getUserId()));
        map.put("username", loginUserVO.getUsername());
        map.put("tenantId", String.valueOf(loginUserVO.getTenantId()));
        map.put("tenantCode", loginUserVO.getTenantCode());
        String token = Auth0JwtUtil.create(authProperties.getSecret(), 7, map);
        JwtAuthVO vo = new JwtAuthVO(token);
        return R.data(vo);
    }


}
