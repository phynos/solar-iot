package com.phynos.framework.front.raw.distributed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author by Lupc
 * @date 2020/4/5.
 */
public class IotNettyRemote {

    Logger logger = LoggerFactory.getLogger(getClass());

    private IotNettyNode node;

    public IotNettyRemote(IotNettyNode n) {
        this.node = n;
    }

    public void writeAndFlush(Object pkg) {

    }

    public IotNettyNode getNode() {
        return node;
    }

}
