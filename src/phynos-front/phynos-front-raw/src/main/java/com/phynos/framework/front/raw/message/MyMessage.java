package com.phynos.framework.front.raw.message;

public class MyMessage {

	private byte[] head;
	
	private byte[] messageType;
	
	private int length;
	
	private byte[] data;
	
	public MyMessage() {
		head = new byte[4];
		messageType = new byte[4];
	}

	public byte[] getHead() {
		return head;
	}

	public void setHead(byte[] head) {
		this.head = head;
	}

	public byte[] getMessageType() {
		return messageType;
	}

	public void setMessageType(byte[] messageType) {
		this.messageType = messageType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}	
	
	
}
