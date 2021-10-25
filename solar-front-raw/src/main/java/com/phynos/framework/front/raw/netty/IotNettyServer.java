package com.phynos.framework.front.raw.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * 一个典型的netty应用
 *
 * @author phynos
 */
@Slf4j
public class IotNettyServer {

    private final Integer port;

    IotNettyChannelInitializer iotNettyChannelInitializer;

    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    ServerBootstrap b = new ServerBootstrap(); // (2)

    public IotNettyServer(Integer port) {
        this.port = port;
    }

    public void close() {
        log.info("关闭服务器....");
        //优雅退出
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public void start() throws Exception {
        log.info("netty监听端口：{}", port);
        try {
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(iotNettyChannelInitializer)
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public void setIotNettyChannelInitializer(IotNettyChannelInitializer iotNettyChannelInitializer) {
        this.iotNettyChannelInitializer = iotNettyChannelInitializer;
    }

}
