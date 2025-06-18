package com.phynos.solar.auth.service.impl;

import com.phynos.solar.auth.user.TenantDetailService;
import com.phynos.solar.auth.service.TenantLoginService;
import lombok.extern.slf4j.Slf4j;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/11/3 15:47
 */
@Slf4j
public class TenantLoginServiceImpl implements TenantLoginService {

    private final TenantDetailService tenantDetailService;

    public TenantLoginServiceImpl(TenantDetailService tenantDetailService) {
        this.tenantDetailService = tenantDetailService;
    }

}
