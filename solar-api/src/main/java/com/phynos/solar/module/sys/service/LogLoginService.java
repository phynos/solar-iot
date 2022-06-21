package com.phynos.solar.module.sys.service;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/6/21 09:57
 */
public interface LogLoginService {


    void insertLog(long userId, String[] ips, int platform);


}
