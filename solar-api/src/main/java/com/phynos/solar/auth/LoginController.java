package com.phynos.solar.auth;

import com.phynos.solar.auth.jwttoken.dto.LoginDTO;
import com.phynos.solar.auth.jwttoken.vo.JwtAuthVO;
import com.phynos.solar.auth.kaptcha.KaptchaService;
import com.phynos.solar.util.json.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    KaptchaService kaptchaService;

    @PostMapping("")
    public R<JwtAuthVO> tokenAuth(@RequestBody @Valid LoginDTO dto) {
        return R.data(null);
    }


}
