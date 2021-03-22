package com.phynos.framework.front.raw.message.reponse;

import com.phynos.framework.front.raw.annotation.IotMsgType;
import com.phynos.framework.front.raw.message.IotMessage;

@IotMsgType(msgType = 2)
public class IotLoginResultMessage extends IotMessage {

    @Override
    public byte[] getData() {
        return new byte[0];
    }

    @Override
    public void setData(byte[] data) {

    }

}
