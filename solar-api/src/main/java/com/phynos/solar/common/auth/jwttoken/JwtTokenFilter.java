package com.phynos.solar.common.auth.jwttoken;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.phynos.solar.common.auth.CustomUserDetails;
import com.phynos.solar.common.auth.UserAuthService;
import com.phynos.solar.common.auth.jwttoken.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TOKEN过滤器
 *
 * @author lupc
 * @date 2021/7/1 18:17
 */
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    String tokenHead = "Bearer ";
    String tokenHeader = "Authorization";

    @Autowired
    TokenService tokenService;
    @Autowired
    UserAuthService userAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("token过滤器：{}", request.getRequestURI());
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            if (StringUtils.isNotEmpty(authToken) && SecurityContextHolder.getContext().getAuthentication() == null) {
                DecodedJWT token = tokenService.verify(authToken);
                if (token != null) {
                    CustomUserDetails userDetails = tokenService.getLoginUser(token);
                    UsernamePasswordAuthenticationToken authentication = userAuthService.createUpAuthTokenByUs(userDetails);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("用户认证成功={}，setting security context", userDetails.getUsername());
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
