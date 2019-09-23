package com.phynos.framework.simulator.raw.message;

public interface IMessageContent {

	byte getCommandType();
	
	int getLength();
	
	byte[] getData();
	
}
