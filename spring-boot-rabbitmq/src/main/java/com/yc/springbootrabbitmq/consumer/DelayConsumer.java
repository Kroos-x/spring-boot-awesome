package com.yc.springbootrabbitmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.yc.springbootrabbitmq.common.Const;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 功能描述: 延迟消息监听
 *
 * @Author: xieyc
 * @Date: 2020-12-15
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DelayConsumer {

    // 40天的毫秒数
    private final static long LIMIT_DAY = 3456000000L;


    @RabbitHandler
    @RabbitListener(queues = Const.Queue.DELAY_QUEUE)
    public void onMessage(String msg, Channel channel, Message message) throws IOException {
        JSONObject msgBody = JSONObject.parseObject(msg, JSONObject.class);
        log.info(msgBody + "");
        /**
         * 发送消息时可携带是否处理标识(通过执行时间和RabbitMQ延迟消息极限时间对比)
         *      true:处理消息,false: 不处理,继续发送消息
         */
        Boolean execFlag = msgBody.getBoolean("execFlag");
        String endTimeStr = msgBody.getString("endTime");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, df);
        if (execFlag) {
            // 执行业务逻辑
        } else {
            // 判断结束时间距离现在时间
            Duration duration = Duration.between(LocalDateTime.now(), endTime);
            // 相差的毫秒数
            long millisNum = duration.toMillis();
            if (millisNum > LIMIT_DAY) {
                // 相差时间大于极限时间下次不处理
            } else {
                // 下次处理
            }
        }
        // 手动确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
