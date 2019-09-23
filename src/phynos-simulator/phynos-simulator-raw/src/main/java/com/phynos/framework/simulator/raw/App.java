package com.phynos.framework.simulator.raw;

import com.phynos.framework.simulator.raw.app.MySessionInfo;
import com.phynos.framework.simulator.raw.message.write.LoginMessage;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final long CONNECT_TIMEOUT = 30*1000L; // 30 seconds

	public static final String HOSTNAME = "localhost";
	public static final int PORT = 4099;

	private static final int IDEL_TIME = 10;
	
	private static final String TERMINATE_ID = "solar001";
	
	public static void main(String[] args) throws Throwable {
		    
		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);

		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

		connector.getFilterChain().addLast("logger", new LoggingFilter());
		//配置
		SocketSessionConfig cfg = connector.getSessionConfig();
		cfg.setIdleTime(IdleStatus.BOTH_IDLE, IDEL_TIME);
		
		connector.setHandler(new ClientSessionHandler());
		IoSession session;

		for (;;) {
			try {
				ConnectFuture future = connector.connect(new InetSocketAddress(HOSTNAME, PORT));
				//等待连接成功
				future.awaitUninterruptibly();
				session = future.getSession();
				break;
			} catch (RuntimeIoException e) {
				System.err.println("ailed to connect.");
				e.printStackTrace();
				Thread.sleep(5000);
			}
		}
		
		//发送登录请求
		LoginMessage lm = new LoginMessage(TERMINATE_ID,"test","123456");
		//绑定基本会话消息
		MySessionInfo si = new MySessionInfo(TERMINATE_ID);
		session.setAttribute(MySessionInfo.KEY_SESSSION, si);
		session.write(lm);

		//等待连接关闭
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}
}
