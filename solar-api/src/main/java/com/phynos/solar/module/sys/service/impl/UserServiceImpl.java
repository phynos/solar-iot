package com.phynos.solar.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phynos.solar.common.mybatisplus.MybatisPlusConfig;
import com.phynos.solar.module.sys.entity.User;
import com.phynos.solar.module.sys.mapper.UserMapper;
import com.phynos.solar.module.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    UserMapper userMapper;

    @PostConstruct
    public void test() {
//        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
//
//        List<User> data = userMapper.selectList(queryWrapper);
//        data.forEach(user -> log.info(user.toString()));
    }

    @Override
    public Page<User> pageList() {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        Page<User> page = new Page<>();
        page = userMapper.selectPage(page, queryWrapper);
        return page;
    }

    @Override
    public void checkUser(@Nonnull String username) {

    }

}
