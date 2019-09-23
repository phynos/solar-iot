package com.phynos.framework.simulator.raw;

import com.phynos.framework.simulator.raw.app.MySessionInfo;
import com.phynos.framework.simulator.raw.message.read.HeatBeatResultMessage;
import com.phynos.framework.simulator.raw.message.read.LoginResultMessage;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientSessionHandler extends IoHandlerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientSessionHandler.class);
    
    public ClientSessionHandler() {

    }

    @Override
    public void sessionOpened(IoSession session) {
        
    }

    @Override
    public void messageReceived(IoSession session, Object message) {    	
       if(message instanceof LoginResultMessage) {
    	   LoginResultMessage m = (LoginResultMessage)message;
    	   if(m.isOK()){
    		   MySessionInfo si = MySessionInfo.getMySessionInfo(session);
    		   si.login();    		   
    	   } else {
    		   session.closeNow();
    	   }
    	   return;
       } 
       MySessionInfo si = MySessionInfo.getMySessionInfo(session);
       if(si.checkLogin() == false) {
    	   LOGGER.warn("登录超时或没有登录！");
    	   session.closeNow();
    	   return;
       }
       if(message instanceof HeatBeatResultMessage) {
    	   HeatBeatResultMessage m = (HeatBeatResultMessage)message;
    	   LOGGER.info(m.toString());
       } else {
    	   //不支持的消息类型
    	   LOGGER.warn("不支持的消息类型");
       }
    }
    
    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
    		throws Exception {
    	//获取最后数据时间，如果超过规定值，则发送心跳包
    	MySessionInfo m = MySessionInfo.getMySessionInfo(session);
    	m.handleIdle(session);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        session.closeNow();
    }
}
