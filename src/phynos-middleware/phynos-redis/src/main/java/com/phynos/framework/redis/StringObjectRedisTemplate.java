package com.phynos.framework.redis;

import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Author: Lupc
 * @Date: 2019/10/31 9:21
 **/
@Component
public class StringObjectRedisTemplate extends RedisTemplate<String, Object> {

    public StringObjectRedisTemplate(RedisConnectionFactory connectionFactory) {
        setKeySerializer(RedisSerializer.string());
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }

}
