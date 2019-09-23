package com.phynos.framework.front.websocket;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 处理类
 * @author phynos
 *
 */
public class MyWebsocketHandler extends TextWebSocketHandler {

	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		System.out.println(message.getPayload());
	}
	
	@Override
	protected void handleBinaryMessage(WebSocketSession session,
			BinaryMessage message) {
		// TODO Auto-generated method stub
		super.handleBinaryMessage(session, message);
	}
	
	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		super.handleTransportError(session, exception);
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
	
}
