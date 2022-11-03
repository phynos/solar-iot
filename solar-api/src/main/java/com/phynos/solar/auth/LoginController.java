package com.phynos.solar.auth;

import com.phynos.solar.auth.jwttoken.dto.LoginDTO;
import com.phynos.solar.auth.jwttoken.util.Auth0JwtUtil;
import com.phynos.solar.auth.jwttoken.vo.JwtAuthVO;
import com.phynos.solar.auth.kaptcha.KaptchaService;
import com.phynos.solar.auth.service.UserLoginService;
import com.phynos.solar.auth.vo.TokenInfo;
import com.phynos.solar.util.json.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    UserLoginService userLoginService;

    @PostMapping("")
    public R<JwtAuthVO> tokenAuth(@RequestBody @Valid LoginDTO dto) throws AuthException {
        return login(dto);
    }

    @GetMapping("")
    public R<JwtAuthVO> tokenAuth2(@Valid LoginDTO dto) throws AuthException {
        return login(dto);
    }

    private R<JwtAuthVO> login(LoginDTO dto) throws AuthException {
        TokenInfo tokenInfo = userLoginService.login(dto);
        Map<String, String> map = tokenInfo.toMap();
        String token = Auth0JwtUtil.create(authProperties.getSecret(), 7, map);
        JwtAuthVO vo = new JwtAuthVO(token);
        return R.data(vo);
    }


}
