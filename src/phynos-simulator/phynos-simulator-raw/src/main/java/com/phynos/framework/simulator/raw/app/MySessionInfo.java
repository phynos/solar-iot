package com.phynos.framework.simulator.raw.app;

import com.phynos.framework.simulator.raw.message.write.HeatBeatMessage;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 会话信息
 * @author phynos
 *
 */
public class MySessionInfo {

	private final static Logger LOGGER = LoggerFactory.getLogger(MySessionInfo.class);
	
	public static final String KEY_SESSSION = "MySessionInfo";
	
	private static final long MAX_IDEL_TIME = 3 * 60 * 1000l;
	
	/**
     * 终端ID
     */
    private String terminateId;
	
    private boolean mIsLogin;
    
	private Date lastDataTime;	
	
	public MySessionInfo(String terminateId){
		this.terminateId = terminateId;
		this.mIsLogin = false;
		lastDataTime = new Date();
	}
	
	public static MySessionInfo getMySessionInfo(IoSession session){
		if(!session.containsAttribute(KEY_SESSSION))
			return null;
		MySessionInfo m = (MySessionInfo)session.getAttribute(KEY_SESSSION);
		return m;
	}
	
	/**
	 * 检查 是否超过最大空闲时间
	 * @return
	 */
	public boolean checkIdle(){
		long now = System.currentTimeMillis();
		if((now - lastDataTime.getTime()) >= MAX_IDEL_TIME)
			return true;
		else
			return false;
	}
	
	public void handleIdle(IoSession session){
		if(checkIdle()){
			HeatBeatMessage message = new HeatBeatMessage();
			session.write(message);
		} else {
			//当前是合理的空闲时间
		}
	}
	
	public void login(){
		LOGGER.info("登录成功。。。");
		mIsLogin = true;
	}
	
	public boolean checkLogin(){
		return mIsLogin;
	}
	
}
