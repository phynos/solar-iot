package com.phynos.framework.front.raw.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Service;

/**
 * 心跳处理器
 * @author Lupc
 *
 */
@Service
public class IotNettyHeartBeatHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		super.userEventTriggered(ctx, evt);
		if(evt instanceof IdleStateEvent) {
			//发送心跳

		}
	}
	
}
