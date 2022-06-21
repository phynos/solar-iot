package com.phynos.solar.module.sys.service;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/6/21 09:57
 */
public interface LogLoginService {

    /**
     * 流式查询测试
     *
     * <ul>
     *     <li>对于postgresql，必须开启事务</li>
     *     <li>对于mysql，本身不支持，只是从网络接口上一个个传输</li>
     * </ul>
     *
     */
    void testStreamQuery();

    void testBatchInsert();

    void insertLog(long userId, String[] ips, int platform);


}
