package com.phynos.framework.web.api.shiro;

import com.phynos.framework.redis.StringObjectRedisTemplate;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 基于Redis存储的shiro session存储
 * 这里继承自EnterpriseCacheSessionDAO，可以用内存缓存会话，防止对redis过多的访问
 * @Author: Lupc
 * @Date: 2019/10/24 13:57
 **/
@Component
public class RedisSessionDao extends EnterpriseCacheSessionDAO {

    @Autowired
    StringObjectRedisTemplate stringObjectRedisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        SimpleSession session1 = (SimpleSession) session;
        session1.setId(sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String key = convertSessionId(sessionId);
        Session o = (Session) stringObjectRedisTemplate.opsForValue().get(key);
        return o;
    }

    @Override
    protected void doUpdate(Session session) {
        String key = convertSessionId(session.getId());
        if (session instanceof ValidatingSession) {
            if (((ValidatingSession) session).isValid()) {
                stringObjectRedisTemplate.opsForValue().set(key, session,session.getTimeout(), TimeUnit.MILLISECONDS);
            } else {
                stringObjectRedisTemplate.delete(key);
            }
        } else {
            stringObjectRedisTemplate.opsForValue().set(key, session, session.getTimeout(), TimeUnit.MILLISECONDS);
        }
    }

    @Override
    protected void doDelete(Session session) {
        String key = convertSessionId(session.getId());
        stringObjectRedisTemplate.delete(key);
    }

    private String convertSessionId(Serializable sessionId){
        return "sessionId-" + sessionId.toString();
    }

}
