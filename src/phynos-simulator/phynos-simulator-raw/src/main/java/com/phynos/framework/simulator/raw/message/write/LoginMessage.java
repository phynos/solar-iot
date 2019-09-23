package com.phynos.framework.simulator.raw.message.write;


import com.phynos.framework.simulator.raw.message.AbstractMessage;

public class LoginMessage extends AbstractMessage {

	
	/**
	 * 命令字
	 */
	public static final byte CMD_TYPE = 0x00;
	
	private String username;
	
	private String password;

	public LoginMessage(String terminateId,String username,String password){
		this.username = username;
		this.password = password;
	}	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
