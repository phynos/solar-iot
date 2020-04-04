package com.phynos.framework.front.raw.message;

public abstract class IotMessage {

    public static byte[] HEAD = {0x01, 0x02, 0x03, 0x04};

    private int messageType;

    private int length;

    public IotMessage() {
        messageType = 0;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getLength() {
        return getData().length + 4;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public abstract byte[] getData();

    public abstract void setData(byte[] data);

}
