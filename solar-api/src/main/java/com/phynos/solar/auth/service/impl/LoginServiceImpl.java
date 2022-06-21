package com.phynos.solar.auth.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.phynos.solar.auth.AuthException;
import com.phynos.solar.auth.jwttoken.dto.LoginDTO;
import com.phynos.solar.auth.vo.LoginUserVO;
import com.phynos.solar.auth.vo.TokenInfo;
import com.phynos.solar.auth.service.LoginService;
import com.phynos.solar.util.web.SpringWebUtil;
import com.phynos.solar.module.sys.entity.User;
import com.phynos.solar.module.sys.mapper.UserMapper;
import com.phynos.solar.util.json.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/4/26 08:56
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    UserMapper userMapper;

    @Override
    public LoginUserVO login(LoginDTO dto) throws AuthException {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getUsername, dto.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        //检查用户是否存在
        if (user == null) {
            throw new AuthException(ResultCodeEnum.USERNAME_PASSWORD_MISMATCH);
        }
        //检查用户是否被禁用
        if (!user.getEnable()) {
            throw new AuthException(ResultCodeEnum.USER_DISABLED);
        }
        //检查用户是否密码匹配

        HttpServletRequest request = SpringWebUtil.getHttpServletRequest();
        return loginSuccess(request, user);
    }

    @Override
    public TokenInfo authSuccess(HttpServletRequest request, DecodedJWT jwt) {
        //将租户id放入当前请求
        Long tenantId = Long.valueOf(jwt.getClaim("tenantId").asString());
        request.setAttribute("tenantId", tenantId);
        return null;
    }

    @Override
    public LoginUserVO loginSuccess(HttpServletRequest request, User user) {
        request.setAttribute("tenantId", user.getTenantId());
        return LoginUserVO.fromDO(user);
    }

}
