package com.phynos.framework.push.rabbit;

import com.phynos.framework.push.domain.WebSocketServer;
import com.phynos.framework.push.rabbitmq.config.RabbitConfig;
import com.phynos.framework.push.rabbitmq.model.NoticeMessage;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: Lupc
 * @Date: 2019/12/11 17:13
 **/
@Component
public class RabbitConsumer {

    private static Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);

    private static RabbitConsumer rabbitConsumer;

    @Resource
    private WebSocketServer webSocketServer; //引入WebSocket

    @PostConstruct
    public void init() {
        rabbitConsumer = this;
        rabbitConsumer.webSocketServer = webSocketServer;
    }

    @RabbitListener(queues = RabbitConfig.msg_queue) //监听队列
    public void msgReceive(String content, Message message, Channel channel) throws IOException {
        logger.info("----------------接收到消息--------------------" + content);
        //发送给WebSocket 由WebSocket推送给前端
        NoticeMessage noticeMessage = NoticeMessage.convert(content);
        if (noticeMessage != null) {
            int result = rabbitConsumer.webSocketServer.sendMessageByUserId(noticeMessage.getUserId(), content);
            if (result > 0) {
                // 确认消息已接收
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
        } else {
            logger.error("消息格式转换错误！");
        }
    }


}
