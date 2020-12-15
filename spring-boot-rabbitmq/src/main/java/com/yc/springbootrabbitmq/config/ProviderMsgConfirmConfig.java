package com.yc.springbootrabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: 生产者推送消息的消息确认回调 配置
 *
 *
 * @Author: xieyc
 * @Date: 2020-12-13
 */
@Slf4j
@Configuration
public class ProviderMsgConfirmConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 开启Mandatory,无论推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, b, s) -> {
            log.info("provide ConfirmCallback 相关数据：" + correlationData);
            log.info("provide ConfirmCallback 确认情况：" + correlationData);
            log.info("provide ConfirmCallback 原因：" + s);
        });

        rabbitTemplate.setReturnsCallback(returnedMessage -> log.info("returnsCallback 信息：" + returnedMessage.toString()));

        return rabbitTemplate;
    }

    // 生产者推送消息分为四种情况

    /**
     * ①消息推送到server，但是在server里找不到交换机
     *      // 测试推送一个不存在的交换机
     *      @GetMapping("/TestMessageAck")
     *     public String TestMessageAck() {
     *         String messageId = String.valueOf(UUID.randomUUID());
     *         String messageData = "message: non-existent-exchange test message ";
     *         Map<String, Object> map = new HashMap<>();
     *         map.put("messageId", messageId);
     *         map.put("messageData", messageData);
     *         rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
     *         return "ok";
     *     }
     *
     *  返回结果:
     * ConfirmCallback:     相关数据：null
     * ConfirmCallback:     确认情况：false
     * ConfirmCallback:     原因：channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'non-existent-exchange' in vhost 'JCcccHost', class-id=60, method-id=40)
     *
     *  结论: 触发了ConfirmCallback 函数
     */

    /**
     * ②消息推送到server，找到交换机，没找到队列
     *  新建lonelyDirectExchange 交换机,不绑定队列
     *
     *      @GetMapping("/TestMessageAck2")
     *     public String TestMessageAck2() {
     *         String messageId = String.valueOf(UUID.randomUUID());
     *         String messageData = "message: lonelyDirectExchange test message ";
     *         Map<String, Object> map = new HashMap<>();
     *         map.put("messageId", messageId);
     *         map.put("messageData", messageData);
     *         rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
     *         return "ok";
     *     }
     *
     *     ReturnCallback:     消息：(Body:'{createTime=2019-09-04 09:48:01, messageId=563077d9-0a77-4c27-8794-ecfb183eac80, messageData=message: lonelyDirectExchange test message }' MessageProperties [headers={}, contentType=application/x-java-serialized-object, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, deliveryTag=0])
     *     ReturnCallback:     回应码：312
     *     ReturnCallback:     回应信息：NO_ROUTE
     *     ReturnCallback:     交换机：lonelyDirectExchange
     *     ReturnCallback:     路由键：TestDirectRouting
     *
     *     ConfirmCallback:     相关数据：null
     *     ConfirmCallback:     确认情况：true
     *     ConfirmCallback:     原因：null
     *
     *     结论：两个函数都被调用,但是ConfirmCallback对消息确认情况是true；RetrunCallback提示 NO_ROUTE
     */

    /**
     * ③消息推送到sever，交换机和队列都没找到
     * 结论： 触发的是 ConfirmCallback 回调函数
     */

    /**
     * ④消息推送成功
     *
     * ConfirmCallback:     相关数据：null
     * ConfirmCallback:     确认情况：true
     * ConfirmCallback:     原因：null
     *
     * 结论：触发的是 ConfirmCallback 回调函数。
     */


}
