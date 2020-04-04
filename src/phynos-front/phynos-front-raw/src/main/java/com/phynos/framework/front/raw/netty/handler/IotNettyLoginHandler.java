package com.phynos.framework.front.raw.netty.handler;

import com.phynos.framework.front.raw.message.request.IotLoginMessage;
import com.phynos.framework.front.raw.netty.IoSession;
import com.phynos.framework.front.raw.util.ChannelUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@ChannelHandler.Sharable
public class IotNettyLoginHandler extends ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    public static AttributeKey<IoSession> SESSION_KEY = AttributeKey.valueOf("session");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        IoSession ioSession = new IoSession();
        if (!ChannelUtils.addChannelSession(ctx.channel(), ioSession)) {

        }
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        IoSession ioSession = ChannelUtils.getSessionBy(ctx.channel());
        if (msg instanceof IotLoginMessage) {
            IotLoginMessage iotMessage = (IotLoginMessage) msg;
            logger.debug("登录消息，消息码={}，消息类型={}",
                    iotMessage.getMessageType(),
                    iotMessage.getClass().getName());
            //判断是否已经登录，如果已经登录，则直接关闭链接

            //执行登录

            //记录登录状态
            ioSession.isLogin = true;
        } else if (!ioSession.isLogin) {
            logger.warn("没有登录，直接关闭连接");
            ctx.channel().close();
        } else {
            super.channelRead(ctx, msg);
        }
    }

}
