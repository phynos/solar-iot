package com.phynos.framework.front.raw.netty;

import com.phynos.framework.front.raw.message.MyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IotNettyEncoder extends MessageToByteEncoder<MyMessage> {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void encode(ChannelHandlerContext ctx, MyMessage msg, ByteBuf out) throws Exception {
		out.writeBytes(msg.getHead());
		out.writeBytes(msg.getMessageType());
		out.writeInt(msg.getLength());
		out.writeBytes(msg.getData());
	}
	
}
