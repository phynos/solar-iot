package com.phynos.framework.front.raw.distributed;

import java.io.Serializable;
import java.util.Objects;

/**
 * netty分布式（zk）节点
 *
 * @author by Lupc
 * @date 2020/4/5.
 */
public class IotNettyNode implements Comparable<IotNettyNode>, Serializable {

    //worker 的Id,zookeeper负责生成
    private long id;

    //Netty 服务 的连接数
    private Integer balance = 0;

    //Netty 服务 IP
    private String host;

    //Netty 服务 端口
    private Integer port;

    public IotNettyNode() {
    }

    public IotNettyNode(String host, Integer port) {
        this.host = host;
        this.port = port;
    }


    @Override
    public String toString() {
        return "IotNettyNode{" +
                "id='" + id + '\'' +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ",balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IotNettyNode node = (IotNettyNode) o;
//        return id == node.id &&
        return Objects.equals(host, node.host) &&
                Objects.equals(port, node.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, host, port);
    }

    /**
     * 升序排列
     */
    public int compareTo(IotNettyNode o) {
        int weight1 = this.balance;
        int weight2 = o.balance;
        if (weight1 > weight2) {
            return 1;
        } else if (weight1 < weight2) {
            return -1;
        }
        return 0;
    }


    public void incrementBalance() {
        balance++;
    }

    public void decrementBalance() {
        balance--;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
