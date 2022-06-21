package com.phynos.solar.auth;

import com.phynos.solar.util.web.SpringWebUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/5/24 10:41
 */
public class TenantConext {

    /**
     * 返回当前租户id
     * 登录之后才能调用
     *
     * @return
     */
    public static Long getTenantId() {
        HttpServletRequest request = SpringWebUtil.getHttpServletRequest();
        Long tenantId = (Long) request.getAttribute("tenantId");
        return tenantId;
    }

}
