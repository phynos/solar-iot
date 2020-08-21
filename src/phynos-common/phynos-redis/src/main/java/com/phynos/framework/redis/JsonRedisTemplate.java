package com.phynos.framework.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Author: Lupc
 * @Date: 2019/10/31 9:20
 **/
@Component
public class JsonRedisTemplate extends RedisTemplate<String, Object> {

    public JsonRedisTemplate(RedisConnectionFactory connectionFactory) {
        setKeySerializer(RedisSerializer.string());
        //设置序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        setValueSerializer(jackson2JsonRedisSerializer);//value序列化
        setHashKeySerializer(RedisSerializer.string());//Hash key序列化
        setHashValueSerializer(jackson2JsonRedisSerializer);//Hash value序列化
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }

}
