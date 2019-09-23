package com.phynos.framework.simulator.raw.codec;

import com.phynos.framework.simulator.raw.message.AbstractMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 * 编码器
 * @author phynos
 *
 * @param <T>
 */
public class SolarMessageEncoder<T extends AbstractMessage> implements MessageEncoder<T> {

	protected SolarMessageEncoder() {

	}

	public void encode(IoSession session, T message, ProtocolEncoderOutput out) throws Exception {
		//获取 数据
		byte[] buffer  = message.encode();
		//准备发送
		IoBuffer buf = IoBuffer.wrap(buffer);
		//最终发送
		out.write(buf);
	}

}