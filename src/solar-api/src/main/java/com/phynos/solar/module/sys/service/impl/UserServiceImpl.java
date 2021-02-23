package com.phynos.solar.module.sys.service.impl;

import com.phynos.solar.module.sys.entity.User;
import com.phynos.solar.module.sys.mapper.UserMapper;
import com.phynos.solar.module.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
