package com.phynos.framework.simulator.raw.codec;

import com.phynos.framework.simulator.raw.message.SolarReadMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * 解码器
 * @author phynos
 *
 */
public class SolarMessageDecoder implements MessageDecoder {


    protected SolarMessageDecoder() {
        
    }

    public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
        
        return MessageDecoderResult.NOT_OK;
    }

    public MessageDecoderResult decode(IoSession session, IoBuffer in,
            ProtocolDecoderOutput out) throws Exception {
        //一些检查
    	
    	//这里真正做消息转换
    	SolarReadMessage message = SolarReadMessage.getMessage();
    	out.write(message);
    	
        return MessageDecoderResult.OK;
    }

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
		
	}

}
