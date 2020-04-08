package com.phynos.framework.core;

/**
 * 用户会话接口，登录后可随时调用
 *
 * @author Lupc
 **/
public interface IUserSession<T> {

    /**
     * 返回用户ID
     * @return
     */
    T getUserId();

    /**
     * 返回用户名称
     * @return
     */
    String getUsername();

}
