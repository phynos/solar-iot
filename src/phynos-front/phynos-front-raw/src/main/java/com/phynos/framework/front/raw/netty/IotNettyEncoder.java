package com.phynos.framework.front.raw.netty;

import com.phynos.framework.front.raw.message.IotMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编码器
 * @author lupc
 */
public class IotNettyEncoder extends MessageToByteEncoder<IotMessage> {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void encode(ChannelHandlerContext ctx, IotMessage msg, ByteBuf out) throws Exception {
		out.writeBytes(IotMessage.HEAD);
		out.writeInt(msg.getMessageType());
		out.writeInt(msg.getLength());
		out.writeBytes(msg.getData());
	}
	
}
