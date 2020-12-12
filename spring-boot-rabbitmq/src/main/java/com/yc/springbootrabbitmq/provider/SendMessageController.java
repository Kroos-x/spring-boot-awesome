package com.yc.springbootrabbitmq.provider;

import com.yc.springbootrabbitmq.common.Const;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 功能描述: 测试接口
 *
 * @Author: xieyc
 * @Date: 2020-12-01
 */
@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    // =========== 直连消息 =================

    /**
     * 发送直连消息
     *
     * @return
     */
    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        Map<String, Object> map = new HashMap<>(16);
        map.put("messageId", messageId);
        map.put("messageData", messageData);

        // 将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend(Const.Exchange.DIRECT_EXCHANGE, Const.Routing.DIRECT_ROUTING, map);
        return "ok";
    }

    // ============== 主题消息 =================

    /**
     * 发送主题消息1
     *
     * @return
     */
    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        Map<String, Object> manMap = new HashMap<>(16);
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        rabbitTemplate.convertAndSend(Const.Exchange.TOPIC_EXCHANGE, Const.Routing.MAN, manMap);
        return "ok";
    }

    /**
     * 发送主题消息2
     *
     * @return
     */
    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        Map<String, Object> womanMap = new HashMap<>(16);
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        rabbitTemplate.convertAndSend(Const.Exchange.TOPIC_EXCHANGE, Const.Routing.WOMAN, womanMap);
        return "ok";
    }


}
