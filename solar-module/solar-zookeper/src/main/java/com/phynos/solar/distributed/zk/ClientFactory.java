package com.phynos.solar.distributed.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author by Lupc
 * @date 2020/4/5.
 */
public class ClientFactory {

    /**
     * @param connectionString zk的连接地址
     * @return CuratorFramework 实例
     */
    public static CuratorFramework createSimple(String connectionString) {
        // 重试策略:第一次重试等待1s，第二次重试等待2s，第三次重试等待4s
        // 第一个参数：等待时间的基础单位，单位为毫秒
        // 第二个参数：最大重试次数
        ExponentialBackoffRetry retryPolicy =
                new ExponentialBackoffRetry(1000, 3);

        // 获取 CuratorFramework 实例的最简单的方式
        // 第一个参数：zk的连接地址
        // 第二个参数：重试策略
        return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
    }

    /**
     * @param connectionString    zk的连接地址
     * @param retryPolicy         重试策略
     * @param connectionTimeoutMs 连接
     * @param sessionTimeoutMs
     * @return CuratorFramework 实例
     */
    public static CuratorFramework createWithOptions(
            String connectionString, RetryPolicy retryPolicy,
            int connectionTimeoutMs, int sessionTimeoutMs) {

        // builder 模式创建 CuratorFramework 实例
        return CuratorFrameworkFactory.builder()
                .connectString(connectionString)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                // 其他的创建选项
                .build();
    }
}
