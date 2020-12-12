package com.yc.springbootrabbitmq.consumer;

import com.yc.springbootrabbitmq.common.Const;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 功能描述: 主题交换机 消费者监听 man
 *
 * @Author: xieyc
 * @Date: 2020-12-11
 */
@Component
@RabbitListener(queues = Const.Queue.TOPIC_MAN)
public class TopicManConsumer {

    @RabbitHandler
    public void process(Map message) {
        System.out.println("=============================");
        System.out.println("TopicManReceiverMan消费者收到消息  : " + message.toString());
        System.out.println("=============================");
    }


}
