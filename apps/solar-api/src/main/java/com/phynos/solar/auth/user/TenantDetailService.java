package com.phynos.solar.auth.user;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/11/3 15:43
 */
public interface TenantDetailService {

    TenantDetails loadUserByUsername(String username);

}
