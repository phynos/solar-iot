package com.phynos.framework.front.raw.distributed;

import com.phynos.framework.front.raw.config.ServerConstants;
import com.phynos.framework.front.raw.message.IotMessage;
import com.phynos.framework.front.raw.util.JsonUtil;
import com.phynos.framework.front.raw.zk.ZKclient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 通过zk的watch实现服务的上线和下线通知
 * @author by Lupc
 * @date 2020/4/4.
 */
public class IotNettyRemoteManager {

    Logger logger = LoggerFactory.getLogger(getClass());

    //Zk客户端
    private CuratorFramework client = null;

    private ConcurrentHashMap<Long, IotNettyRemote> remoteMap =
            new ConcurrentHashMap<>();

    private static IotNettyRemoteManager singleInstance = null;
    private static final String path = ServerConstants.MANAGE_PATH;

    public static IotNettyRemoteManager getInst() {
        if (null == singleInstance) {
            singleInstance = new IotNettyRemoteManager();
            singleInstance.client = ZKclient.instance.getClient();
        }
        return singleInstance;
    }

    private IotNettyRemoteManager() {

    }

    /**
     * 初始化节点管理
     */
    public void init() {
        try {
            //订阅节点的增加和删除事件
            PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);
            PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client,
                                       PathChildrenCacheEvent event) throws Exception {
                    logger.info("开始监听其他的ImWorker子节点:-----");
                    ChildData data = event.getData();
                    switch (event.getType()) {
                        case CHILD_ADDED:
                            logger.info("CHILD_ADDED : " + data.getPath() + "  数据:" + data.getData());
                            processNodeAdded(data);
                            break;
                        case CHILD_REMOVED:
                            logger.info("CHILD_REMOVED : " + data.getPath() + "  数据:" + data.getData());
                            processNodeRemoved(data);
                            break;
                        case CHILD_UPDATED:
                            logger.info("CHILD_UPDATED : " + data.getPath() + "  数据:" + new String(data.getData()));
                            break;
                        default:
                            logger.debug("[PathChildrenCache]节点数据为空, path={}", data == null ? "null" : data.getPath());
                            break;
                    }
                }
            };
            childrenCache.getListenable().addListener(childrenCacheListener);
            System.out.println("Register zk watcher successfully!");
            childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processNodeRemoved(ChildData data) {
        byte[] payload = data.getData();
        IotNettyNode n = JsonUtil.jsonBytes2Object(payload, IotNettyNode.class);

        long id = IotWorker.getInst().getIdByPath(data.getPath());
        n.setId(id);
        logger.info("[TreeCache]节点删除, path={}, data={}",
                data.getPath(), n.toString());
        IotNettyRemote peerSender = remoteMap.get(n.getId());
        if (null != peerSender) {
            remoteMap.remove(n.getId());
        }
    }

    private void processNodeAdded(ChildData data) {
        byte[] payload = data.getData();
        IotNettyNode n = JsonUtil.jsonBytes2Object(payload, IotNettyNode.class);

        long id = IotWorker.getInst().getIdByPath(data.getPath());
        n.setId(id);

        logger.info("[TreeCache]节点更新端口, path={}, data={}",
                data.getPath(), n.toString());

        if (n.equals(getLocalNode())) {
            logger.info("[TreeCache]本地节点, path={}, data={}",
                    data.getPath(), n.toString());
            return;
        }
        IotNettyRemote peerSender = remoteMap.get(n.getId());
        if (null != peerSender && peerSender.getNode().equals(n)) {

            logger.info("[TreeCache]节点重复增加, path={}, data={}",
                    data.getPath(), n.toString());
            return;
        }
        peerSender = new IotNettyRemote(n);

        remoteMap.put(n.getId(), peerSender);
    }


    public IotNettyRemote getNettyRemote(long id) {
        IotNettyRemote peerSender = remoteMap.get(id);
        if (null != peerSender) {
            return peerSender;
        }
        return null;
    }

    public void sendNotification(IotMessage message) {
        remoteMap.keySet().stream().forEach(
                key -> {
                    if (!key.equals(getLocalNode().getId())) {
                        IotNettyRemote peerSender = remoteMap.get(key);
                        peerSender.writeAndFlush(message);
                    }
                }
        );

    }

    public IotNettyNode getLocalNode() {
        return IotWorker.getInst().getLocalNodeInfo();
    }

    public void remove(IotNettyNode remoteNode) {
        remoteMap.remove(remoteNode.getId());
        logger.info("[TreeCache]移除远程节点信息,  node={}", remoteNode.toString());
    }

}
