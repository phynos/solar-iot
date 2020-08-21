package com.phynos.framework.push.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Lupc
 * @Date: 2019/12/11 17:24
 **/
@Configuration
public class RabbitConfig {

    //websocket 消息队列
    public static final String msg_queue = "msg_queue";

    //消息交换机
    public static final String msg_exchang = "msg_exchang";

    //消息路由键
    public static final String msg_routing_key = "msg_routing_key";

    /**
     * 消息队列
     * @return
     */
    @Bean
    public Queue msgQueue(){
        return new Queue(msg_queue);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(msg_exchang);
    }

    /**
     * 消息队列绑定消息交换机
     * @return
     */
    @Bean
    public Binding msgBinding(){
        return BindingBuilder.bind(msgQueue()).to(directExchange()).with(msg_routing_key);
    }

}
