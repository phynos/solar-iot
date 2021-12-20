package com.phynos.solar.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phynos.solar.module.sys.entity.User;
import com.phynos.solar.module.sys.mapper.UserMapper;
import com.phynos.solar.module.sys.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;

    @PostConstruct
    public void test() {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getUsername, "admin");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> log.debug(user.getPassword()));
    }

    @Override
    public void checkUser(@Nonnull String username) {

    }

}
