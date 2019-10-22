package com.phynos.framework.front.raw.netty;

import com.phynos.framework.front.raw.netty.handler.MyNettyHeartBeatHandler;
import com.phynos.framework.front.raw.netty.handler.MyNettyLoginHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import io.netty.handler.traffic.TrafficCounter;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.concurrent.TimeUnit;

public class MyNettyChannelInitializer extends ChannelInitializer<SocketChannel> {

	private static final EventExecutorGroup EXECUTOR_GROUOP = new DefaultEventExecutorGroup(Runtime.getRuntime().availableProcessors() * 2 + 1);
	
	private static final GlobalTrafficShapingHandler trafficHandler = new GlobalTrafficShapingHandler(EXECUTOR_GROUOP, 30, 30);
	
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
                    System.out.println("total read: " + (totalRead >> 10) + " KB");
                    System.out.println("total write: " + (totalWrite >> 10) + " KB");
                    System.out.println("流量监控: " + System.lineSeparator() + trafficCounter);
                }
            }
        }).start();        
    }
    
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		//增加流量监测
		ch.pipeline().addLast(trafficHandler);
		// decode
        ch.pipeline().addLast(new MyNettyDecoder(1024, 8, 4, 0, 0));               
        // encoded
        ch.pipeline().addLast(new MyNettyEncoder());
        // 
        ch.pipeline().addLast(new IdleStateHandler(10, 10, 10));
        //
        ch.pipeline().addLast(new MyNettyLoginHandler());
        ch.pipeline().addLast(new MyNettyHeartBeatHandler());
        //
		ch.pipeline().addLast(EXECUTOR_GROUOP,"work-handler",new MyNettyServerHandler());
	}

}

