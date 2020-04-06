package com.phynos.framework.front.raw.service;

import java.rmi.Remote;

/**
 * netty节点间通过RMI通讯，简化RPC
 * @author by lupc
 * @date 2020-04-06 9:19
 */
public interface RmiServerService extends Remote {

    String sayHello(String msg) throws Exception;

}
