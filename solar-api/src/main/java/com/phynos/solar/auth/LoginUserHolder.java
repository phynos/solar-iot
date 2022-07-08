package com.phynos.solar.auth;

import com.phynos.solar.auth.vo.TokenInfo;
import com.phynos.solar.util.web.SpringWebUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * 提供一些静态方法，用于获取当前用户信息
 *
 * @author lupc
 * @date 2022/7/8 09:36
 */
@Slf4j
public final class LoginUserHolder {

    /**
     * 返回当前登录用户的用户信息
     * 这里仅仅返回token包含的信息，其他更多信息请使用用户服务查询
     *
     * @return
     */
    public static TokenInfo getCurrentUser() {
        HttpServletRequest request = SpringWebUtil.getHttpServletRequest();
        TokenInfo tokenInfo = (TokenInfo) request.getAttribute("tokenInfo");
        return tokenInfo;
    }

}
