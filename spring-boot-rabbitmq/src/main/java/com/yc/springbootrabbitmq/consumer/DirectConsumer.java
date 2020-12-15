package com.yc.springbootrabbitmq.consumer;

import com.yc.springbootrabbitmq.common.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 功能描述: 直连消息接受监听类
 *
 * rabbitListener:监听的队列名称
 *
 * @Author: xieyc
 * @Date: 2020-12-01
 */
@Slf4j
@Component
@RabbitListener(queues = Const.Queue.DIRECT_QUEUE)
public class DirectConsumer {

    @RabbitHandler
    public void msg(Map testMsg) {
        log.info("DirectConsumer收到消息:" + testMsg.toString());
    }

    /**
     * 注意:直连交换机是一对一,如果配置多台监听绑定到同一个直连交互的同一个队列.
     *      结果就是实现了轮询的方式,而且不存在重复消费
     */


}
