package com.phynos.solar.module.sys.service.impl;

import com.phynos.solar.module.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;

/**
 * <p>
 * 管理用户表格 服务实现类
 * </p>
 *
 * @author lupc
 * @since 2021-02-23
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {



    @PostConstruct
    public void test() {

    }

    @Override
    public void checkUser(@Nonnull String username) {

    }

}
