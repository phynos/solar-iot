package com.phynos.solar.module.sys.service.impl;

import com.phynos.solar.module.sys.entity.SysTenant;
import com.phynos.solar.module.sys.mapper.SysTenantMapper;
import com.phynos.solar.module.sys.service.SysTenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author lupc
 * @since 2022-11-03
 */
@Service
public class SysTenantServiceImp extends ServiceImpl<SysTenantMapper, SysTenant> implements SysTenantService {

}
