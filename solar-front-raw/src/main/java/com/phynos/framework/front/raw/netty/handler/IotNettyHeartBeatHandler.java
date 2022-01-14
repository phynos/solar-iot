package com.phynos.framework.front.raw.netty.handler;

import com.phynos.framework.front.raw.message.request.IotHeartBeatMessage;
import com.phynos.framework.front.raw.message.request.IotLoginMessage;
import com.phynos.framework.front.raw.netty.IoSession;
import com.phynos.framework.front.raw.util.ChannelUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 心跳处理器
 * @author Lupc
 *
 */
@Slf4j
@ChannelHandler.Sharable
public class IotNettyHeartBeatHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		IoSession ioSession = ChannelUtils.getSessionBy(ctx.channel());
		if(evt instanceof IdleStateEvent) {
			if(!ioSession.isLogin()) {
				//如果没有登录，达到空闲时间，则直接关闭链接
				log.warn("没有登录，达到空闲时间，直接关闭链接");
				ctx.channel().close();
				return;
			}
			log.debug("空闲事件触发...");
			//发送心跳
			IotHeartBeatMessage iotHeartBeatMessage = new IotHeartBeatMessage();
			ctx.channel().writeAndFlush(iotHeartBeatMessage);
		} else {
			ctx.fireUserEventTriggered(evt);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		IoSession ioSession = ChannelUtils.getSessionBy(ctx.channel());
		if (msg instanceof IotHeartBeatMessage) {

		}
	}
}
