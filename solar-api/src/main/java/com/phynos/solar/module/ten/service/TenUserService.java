package com.phynos.solar.module.ten.service;

import com.phynos.solar.auth.user.UserDetailService;
import com.phynos.solar.module.ten.entity.TenUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lupc
 * @since 2022-11-03
 */
public interface TenUserService extends IService<TenUser>, UserDetailService {

}
