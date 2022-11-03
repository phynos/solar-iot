package com.phynos.solar.module.ten.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.phynos.solar.auth.user.UserDetails;
import com.phynos.solar.module.ten.bo.MyUserDetails;
import com.phynos.solar.module.ten.entity.TenUser;
import com.phynos.solar.module.ten.mapper.TenUserMapper;
import com.phynos.solar.module.ten.service.TenUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lupc
 * @since 2022-11-03
 */
@Service
public class TenUserServiceImp extends ServiceImpl<TenUserMapper, TenUser> implements TenUserService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        LambdaQueryWrapper<TenUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TenUser::getUsername, username);
        TenUser tenUser = baseMapper.selectOne(queryWrapper);
        return MyUserDetails.fromTenUser(tenUser);
    }

}
