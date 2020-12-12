package com.yc.springbootrabbitmq.consumer;

import com.yc.springbootrabbitmq.common.Const;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 功能描述: 主题交换机 消费者监听 woman
 *
 * @Author: xieyc
 * @Date: 2020-12-11
 */
@Component
@RabbitListener(queues = Const.Queue.TOPIC_WOMAN)
public class TopicWomanConsumer {

    @RabbitHandler
    public void process(Map message) {
        System.out.println("=============================");
        System.out.println("TopicManReceiverWoman消费者收到消息  : " + message.toString());
        System.out.println("=============================");
    }

}
