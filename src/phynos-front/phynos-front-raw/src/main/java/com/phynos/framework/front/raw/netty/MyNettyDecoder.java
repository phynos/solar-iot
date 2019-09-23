package com.phynos.framework.front.raw.netty;

import com.phynos.server.sampler.message.MyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class MyNettyDecoder extends LengthFieldBasedFrameDecoder {

	public MyNettyDecoder(
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
