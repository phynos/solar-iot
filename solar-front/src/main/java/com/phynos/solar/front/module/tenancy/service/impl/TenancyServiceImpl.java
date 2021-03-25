package com.phynos.solar.front.module.tenancy.service.impl;

import com.phynos.solar.front.module.tenancy.IotTenancy;
import com.phynos.solar.front.module.tenancy.service.TenancyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lupc
 * @date 2021/3/25 19:13
 */
@Slf4j
@Service
public class TenancyServiceImpl implements TenancyService {

    private static final Map<String, IotTenancy> tenancyMap = new HashMap();


    @Override
    public void add(IotTenancy tenancy) {
        tenancyMap.put(tenancy.getTenancyId(), tenancy);
    }

    @Override
    public IotTenancy get(String tenancyId) {
        return tenancyMap.get(tenancyId);
    }

}
