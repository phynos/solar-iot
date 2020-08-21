package com.phynos.framework.push.rabbitmq;

import com.phynos.framework.push.rabbitmq.config.RabbitConfig;
import com.phynos.framework.push.rabbitmq.model.NoticeMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: Lupc
 * @Date: 2019/12/11 17:27
 **/
@Component
public class RabbitProduct {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public RabbitProduct(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //发送消息 推送到websocket    参数自定义 转为String发送消息
    public void sendMSG(String msg) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息对象放入路由对应的队列当中去
        rabbitTemplate.convertAndSend(RabbitConfig.msg_exchang, RabbitConfig.msg_routing_key, msg, correlationId);
    }

    public void sendMSG(NoticeMessage notice) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        String msg = notice.toString();
        //把消息对象放入路由对应的队列当中去
        rabbitTemplate.convertAndSend(RabbitConfig.msg_exchang, RabbitConfig.msg_routing_key, msg, correlationId);
    }


}
