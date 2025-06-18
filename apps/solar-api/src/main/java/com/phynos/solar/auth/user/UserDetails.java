package com.phynos.solar.auth.user;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/11/3 15:55
 */
public interface UserDetails {

    String getUsername();

    String getPassword();

    Boolean isEnabled();

    Long getTenantId();

}
