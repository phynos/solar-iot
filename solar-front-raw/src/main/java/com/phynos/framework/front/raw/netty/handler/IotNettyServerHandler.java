package com.phynos.framework.front.raw.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * 通用消息处理器
 * @author Lupc
 */
@Slf4j
@ChannelHandler.Sharable
public class IotNettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelActive(ctx);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                .remoteAddress();
        log.debug("建立连接：IP={},PORT={}", insocket.getAddress().getHostAddress(), insocket.getPort());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                .remoteAddress();
        log.debug("关闭连接：IP={},PORT={}", insocket.getAddress().getHostAddress(), insocket.getPort());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        log.debug("收到消息----普通处理器");
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
