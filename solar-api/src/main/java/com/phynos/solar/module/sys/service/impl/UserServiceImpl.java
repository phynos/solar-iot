package com.phynos.solar.module.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
        List<User> data = userMapper.selectList(Wrappers.lambdaQuery(User.class));
        data.forEach(user -> log.info(user.toString()));
    }

    @Override
    public void checkUser(@Nonnull String username) {

    }

}
