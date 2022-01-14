package com.phynos.framework.front.raw.netty;

import com.phynos.framework.front.raw.message.IotMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 编码器
 *
 * @author lupc
 */
@Slf4j
public class IotNettyEncoder extends MessageToByteEncoder<IotMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, IotMessage msg, ByteBuf out) throws Exception {
        log.debug("编码器，消息类型={}，消息长度={}", msg.getMessageType(), msg.getLength());
        out.writeBytes(IotMessage.HEAD);
        out.writeInt(msg.getMessageType());
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getData());
    }

}
