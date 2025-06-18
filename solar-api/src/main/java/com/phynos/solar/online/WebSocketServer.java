package com.phynos.solar.online;

import jakarta.annotation.PostConstruct;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lupc
 * @Date: 2019/12/4 12:00
 **/
@Slf4j
@ConditionalOnWebApplication
@ServerEndpoint(value = "/api/wsocket/{userId}")
@Component
public class WebSocketServer {

    private static final AtomicInteger OnlineCount = new AtomicInteger(0);

    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, Session>> livingSession = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        log.info("websocket 加载");
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        log.debug("userId:" + userId + ",sessionId:" + session.getId());
        if (livingSession.containsKey(userId)) {
            livingSession.get(userId).put(session.getId(), session);
        } else {
            ConcurrentHashMap<String, Session> t = new ConcurrentHashMap<>();
            t.put(session.getId(), session);
            livingSession.put(userId, t);
        }
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info("有连接加入，当前连接数为：{}", cnt);
        //sendMessageBySession(session, "连接成功："+ session.getId());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        if (livingSession.contains(userId)) {
            livingSession.get(userId).remove(session.getId());
        }
        int cnt = OnlineCount.decrementAndGet();
        log.info("有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息：{}", message);
        //sendMessageBySession(session, "收到消息，消息内容：" + message);
    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     *
     * @param session
     * @param message
     */
    public boolean sendMessageBySession(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
            return true;
        } catch (Exception e) {
            log.error("发送消息出错：{}", e.getMessage());
            return false;
        }
    }

    public int sendMessageByUserId(String userId, String message) {
        int result = 0;
        ConcurrentHashMap<String, Session> t = livingSession.get(userId);
        if (t != null) {
            for (Map.Entry<String, Session> s : t.entrySet()) {
                Session session = s.getValue();
                if (sendMessageBySession(session, message))
                    result++;
            }
        }
        return result;
    }


}
