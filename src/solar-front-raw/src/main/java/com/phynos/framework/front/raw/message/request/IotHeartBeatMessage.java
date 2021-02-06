package com.phynos.framework.front.raw.message.request;

import com.phynos.framework.front.raw.annotation.IotMsgType;
import com.phynos.framework.front.raw.message.IotMessage;

import java.io.UnsupportedEncodingException;

@IotMsgType(msgType = 3)
public class IotHeartBeatMessage extends IotMessage {

    public static final String MSG_HEAT_BEAT = "heart beat message";

    public IotHeartBeatMessage() {

    }

    @Override
    public byte[] getData() {
        byte[] data = new byte[4];
        try {
            data = MSG_HEAT_BEAT.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void setData(byte[] data) {

    }

}
