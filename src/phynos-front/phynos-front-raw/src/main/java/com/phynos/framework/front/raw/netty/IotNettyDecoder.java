package com.phynos.framework.front.raw.netty;

import com.phynos.framework.front.raw.message.IotMessage;
import com.phynos.framework.front.raw.util.ClassUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解码器
 * @author Lupc
 */
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
		byte[] head = new byte[4];
		frame.readBytes(head);
		int messageType = frame.readInt();
		int length = frame.readInt();
		byte[] data = new byte[length];
		frame.readBytes(data);
		//构造消息体
		Class<?> clazz = ClassUtil.getMsgClassByType(messageType);
		IotMessage message = (IotMessage)clazz.newInstance();
		message.setMessageType(messageType);
		message.setData(data);
		return message;
	}

}
