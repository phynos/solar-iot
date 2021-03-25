package com.phynos.solar.front.module.tenancy.service;

import com.phynos.solar.front.module.tenancy.IotTenancy;

/**
 * 租户服务
 *
 * @author lupc
 * @date 2021/3/25 19:12
 */
public interface TenancyService {

    void add(IotTenancy tenancy);

    IotTenancy get(String tenancyId);

}
