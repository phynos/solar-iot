package com.phynos.solar.common.auth.author;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 自定义权限数据源
 *
 * @author lupc
 * @date 2021/7/15 19:28
 */
@Slf4j
@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 鉴权时会被 {@link AbstractSecurityInterceptor#beforeInvocation(Object)}调用，根据URL找到对应需要的权限
     *
     * @param object 安全对象类型 {@link FilterInvocation}
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        String uri = request.getRequestURI();
        log.info("[资源被访问：根据URL找到权限配置]: {}", uri);
        return null;
    }

    /**
     * 用于被 {@link AbstractSecurityInterceptor}调用，返回所有的 Collection<ConfigAttribute> ，以筛选出不符合要求的attribute
     *
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

}
