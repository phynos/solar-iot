package com.phynos.framework.front.raw.netty.handler;

import com.phynos.framework.front.raw.message.IotMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
@ChannelHandler.Sharable
public class IotNettyServerHandler extends ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelActive(ctx);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                .remoteAddress();
        logger.debug("建立连接：IP={},PORT={}", insocket.getAddress().getHostAddress(), insocket.getPort());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                .remoteAddress();
        logger.debug("关闭连接：IP={},PORT={}", insocket.getAddress().getHostAddress(), insocket.getPort());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if(msg instanceof IotMessage) {
            IotMessage iotMessage = (IotMessage) msg;
            logger.debug("收到消息，消息码={}，消息类型={}",
                    iotMessage.getMessageType(),
                    iotMessage.getClass().getName());
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
