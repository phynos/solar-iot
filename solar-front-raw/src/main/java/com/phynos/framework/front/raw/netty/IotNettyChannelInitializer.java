package com.phynos.framework.front.raw.netty;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import io.netty.handler.traffic.TrafficCounter;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class IotNettyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static final EventExecutorGroup EXECUTOR_GROUOP = new DefaultEventExecutorGroup(Runtime.getRuntime().availableProcessors() * 2 + 1);

    private static final GlobalTrafficShapingHandler trafficHandler = new GlobalTrafficShapingHandler(EXECUTOR_GROUOP, 30, 30);

    private final Map<String, ChannelInboundHandlerAdapter> adapterMap = new HashMap<>();

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    TrafficCounter trafficCounter = trafficHandler.trafficCounter();
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final long totalRead = trafficCounter.cumulativeReadBytes();
                    final long totalWrite = trafficCounter.cumulativeWrittenBytes();
//                    System.out.println("total read: " + (totalRead >> 10) + " KB");
//                    System.out.println("total write: " + (totalWrite >> 10) + " KB");
//                    System.out.println("流量监控: " + System.lineSeparator() + trafficCounter);
                }
            }
        }).start();
    }

    public IotNettyChannelInitializer() {

    }

    public IotNettyChannelInitializer addAdapter(String name, ChannelInboundHandlerAdapter adapter) {
        adapterMap.put(name, adapter);
        return this;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //增加流量监测
        //ch.pipeline().addLast(trafficHandler);
        // decode
        ch.pipeline().addLast(new IotNettyDecoder(1024, 8, 4, 0, 0));
        // encoded
        ch.pipeline().addLast(new IotNettyEncoder());
        // 处理器-空闲触发器
        ch.pipeline().addLast(EXECUTOR_GROUOP, new IdleStateHandler(10, 10, 120, TimeUnit.SECONDS));
        adapterMap.forEach((k,v) -> {
            ch.pipeline().addLast(EXECUTOR_GROUOP, k, v);
        });
    }

}

