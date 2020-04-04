package com.phynos.framework.front.raw.util;

import com.phynos.framework.front.raw.netty.IoSession;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

/**
 * @author by Lupc
 * @date 2020/4/5.
 */
public class ChannelUtils {

    public static AttributeKey<IoSession> SESSION_KEY = AttributeKey.valueOf("session");

    public static boolean addChannelSession(Channel channel, IoSession session) {
        Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
        return sessionAttr.compareAndSet(null, session);
    }

    public static IoSession getSessionBy(Channel channel) {
        Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
        return sessionAttr.get();
    }

    public static String getIp(Channel channel) {
        return ((InetSocketAddress) channel.remoteAddress()).getAddress().toString().substring(1);
    }

}
