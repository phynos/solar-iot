package com.phynos.framework.front.raw.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 心跳处理器
 * @author Lupc
 *
 */
@Service
@ChannelHandler.Sharable
public class IotNettyHeartBeatHandler extends ChannelInboundHandlerAdapter {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof IdleStateEvent) {
			logger.debug("空闲事件触发...");
			//发送心跳


		} else {
			ctx.fireUserEventTriggered(evt);
		}
	}
	
}
