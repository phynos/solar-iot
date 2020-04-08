package com.phynos.framework.core.util;

import com.phynos.framework.core.DefaultUserSessionImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * shiro工具
 *
 * @author lupc
 */
public class ShiroUtil {

    /**
     * 获取登录信息
     *
     * @param fromShrio 是否从shiro获取
     * @param request   如果是基于shiro，则填null
     * @return
     */
    public static DefaultUserSessionImpl getLoginInfo(boolean fromShrio, HttpServletRequest request) {
        if (fromShrio) {
            Subject subject = SecurityUtils.getSubject();
            DefaultUserSessionImpl sc = (DefaultUserSessionImpl) subject.getPrincipal();
            return sc;
        } else {
            if (request == null) {
                request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            }
            HttpSession session = request.getSession(true);
            Object obj = session.getAttribute(DefaultUserSessionImpl.KEY_SESSION_LOGIN);
            DefaultUserSessionImpl sc = (DefaultUserSessionImpl) obj;
            return sc;
        }
    }

}
