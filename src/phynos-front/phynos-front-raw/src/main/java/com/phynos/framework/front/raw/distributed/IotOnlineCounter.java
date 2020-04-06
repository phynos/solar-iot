package com.phynos.framework.front.raw.distributed;


import com.phynos.framework.front.raw.config.ServerConstants;
import com.phynos.framework.front.raw.zk.ZKclient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.RetryNTimes;

/**
 * 分布式计数器
 * @author by Lupc
 * @date 2020/4/5.
 */
public class IotOnlineCounter {

    private static final String PATH =
            ServerConstants.COUNTER_PATH;

    //Zk客户端
    private CuratorFramework client = null;

    //单例模式
    private static IotOnlineCounter singleInstance = null;

    DistributedAtomicLong distributedAtomicLong = null;
    private Long curValue;

    public static IotOnlineCounter getInst() {
        if (null == singleInstance) {
            singleInstance = new IotOnlineCounter();
            singleInstance.client = ZKclient.instance.getClient();
            singleInstance.init();
        }
        return singleInstance;
    }

    private void init() {
        /**
         *  分布式计数器，失败时重试10，每次间隔30毫秒
         */

        distributedAtomicLong = new DistributedAtomicLong(client, PATH, new RetryNTimes(10, 30));
    }

    private IotOnlineCounter() {

    }

    /**
     * 增加计数
     */
    public boolean increment() {
        boolean result = false;
        AtomicValue<Long> val = null;
        try {
            val = distributedAtomicLong.increment();
            result = val.succeeded();
            System.out.println("old cnt: " + val.preValue()
                    + "   new cnt : " + val.postValue()
                    + "  result:" + val.succeeded());
            curValue = val.postValue();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 减少计数
     */
    public boolean decrement() {
        boolean result = false;
        AtomicValue<Long> val = null;
        try {
            val = distributedAtomicLong.decrement();
            result = val.succeeded();
            System.out.println("old cnt: " + val.preValue()
                    + "   new cnt : " + val.postValue()
                    + "  result:" + val.succeeded());
            curValue = val.postValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
