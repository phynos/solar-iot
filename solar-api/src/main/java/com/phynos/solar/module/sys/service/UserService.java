package com.phynos.solar.module.sys.service;

import javax.annotation.Nonnull;

/**
 * <p>
 * 管理用户表格 服务类
 * </p>
 *
 * @author lupc
 * @since 2021-02-23
 */
public interface UserService {

    void checkUser(@Nonnull String username);

}
