package com.phynos.solar.common.auth.author;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 授权过滤器
 * <pre>过滤器顺序依赖字母排序</pre>
 *
 * @author by lupc
 * @date 2020-12-18 11:19
 */
@Slf4j
@Order(4)
@Component
@WebFilter(filterName = "AuthorisationWebFilter",urlPatterns = "/**")
public class AuthorisationWebFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("AuthorisationWebFilter[授权过滤器] init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        log.info("[授权过滤器]uri：{}", uri);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
