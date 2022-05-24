package com.phynos.solar.module.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phynos.solar.module.sys.entity.User;

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

    Page<User> pageList();

    void checkUser(@Nonnull String username);

}
