package com.yc.springbootrabbitmq.config;

import com.rabbitmq.client.Channel;
import com.yc.springbootrabbitmq.common.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 消费者消息接受处理配置
 * <p>
 * 注:此处配置消息接受确认机制会代替原来的RabbitHandler
 *
 * @Author: xieyc
 * @Date: 2020-12-15
 */
@Slf4j
@Component
public class MyAckConsumerConfig implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
            String msg = message.toString();
            //可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
            String[] msgArray = msg.split("'");
            Map<String, String> msgMap = mapStringToMap(msgArray[1].trim(), 2);
            String messageId = msgMap.get("messageId");
            String messageData = msgMap.get("messageData");

            log.info("消费的消息来自队列：" + message.getMessageProperties().getConsumerQueue());
            if (Const.Queue.DIRECT_QUEUE.equals(message.getMessageProperties().getConsumerQueue())) {
                log.info("MyAckConsumer  messageId:" + messageId + "  messageData:" + messageData);
                log.info("执行TestDirectQueue中的消息的业务处理流程......");
            }

            if (Const.Queue.FANOUT_QUEUE_A.equals(message.getMessageProperties().getConsumerQueue())) {
                log.info("MyAckConsumer  messageId:" + messageId + "  messageData:" + messageData);
                log.info("执行fanout.A中的消息的业务处理流程......");
            }

            // 第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(deliveryTag, true);
            // 第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
            // channel.basicReject(deliveryTag, true);
        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }

    /**
     * String 转 Map
     * {key=value,key=value,key=value} 格式转换成map
     *
     * @param str      str
     * @param entryNum 分隔份数
     * @return
     */
    private Map<String, String> mapStringToMap(String str, int entryNum) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",", entryNum);
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }


}
