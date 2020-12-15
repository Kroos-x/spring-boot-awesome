package com.yc.springbootrabbitmq.consumer;

import com.yc.springbootrabbitmq.common.Const;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 功能描述: 扇形交换机消息 消费者
 *
 * @Author: xieyc
 * @Date: 2020-12-13
 */
@Component
@RabbitListener(queues = Const.Queue.FANOUT_QUEUE_A)
public class FanoutAConsumer {

    @RabbitHandler
    public void process(Map msg) {
        System.out.println("Fanout A Consumer 接收到消息:" + msg.toString());
    }

}
