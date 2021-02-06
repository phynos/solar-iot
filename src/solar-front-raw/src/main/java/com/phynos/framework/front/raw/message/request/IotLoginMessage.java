package com.phynos.framework.front.raw.message.request;

import com.phynos.framework.front.raw.annotation.IotMsgType;
import com.phynos.framework.front.raw.message.IotMessage;

@IotMsgType(msgType = 1)
public class IotLoginMessage extends IotMessage {

    @Override
    public byte[] getData() {
        return new byte[0];
    }

    @Override
    public void setData(byte[] data) {

    }

}
