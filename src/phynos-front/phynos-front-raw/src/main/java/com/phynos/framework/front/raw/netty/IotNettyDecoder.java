package com.phynos.framework.front.raw.netty;

import com.phynos.framework.front.raw.message.MyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IotNettyDecoder extends LengthFieldBasedFrameDecoder {

	Logger logger = LoggerFactory.getLogger(getClass());

	public IotNettyDecoder(
			int maxFrameLength, 
			int lengthFieldOffset, 
			int lengthFieldLength, 
			int lengthAdjustment,
			int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {		
		ByteBuf frame = (ByteBuf)super.decode(ctx, in);
		if(frame == null) {
			return null;
		}
		MyMessage message = new MyMessage();
		in.readBytes(message.getHead());
		in.readBytes(message.getMessageType());
		int length = in.readInt();
		message.setLength(length);
		//根据长度
		byte[] data = new byte[length];
		message.setData(data);
		return message;
	}

}
