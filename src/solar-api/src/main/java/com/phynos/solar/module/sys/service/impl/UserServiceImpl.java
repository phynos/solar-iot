package com.phynos.solar.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phynos.solar.module.sys.entity.User;
import com.phynos.solar.module.sys.mapper.UserMapper;
import com.phynos.solar.module.sys.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <p>
 * 管理用户表格 服务实现类
 * </p>
 *
 * @author lupc
 * @since 2021-02-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @PostConstruct
    public void test() {
        List<User> users = getBaseMapper().selectList(null);
    }

}
