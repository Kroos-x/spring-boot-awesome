package com.yc.springbootrabbitmq.config;

import com.yc.springbootrabbitmq.common.Const;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 功能描述: 主题交换机
 *
 * @Author: xieyc
 * @Date: 2020-12-11
 */
@Configuration
public class TopicRabbitConfig {

    /**
     * 队列1
     *
     * @return
     */
    @Bean
    public Queue manQueue() {
        return new Queue(Const.Queue.TOPIC_MAN);
    }

    /**
     * 队列2
     *
     * @return
     */
    @Bean
    public Queue womanQueue() {
        return new Queue(Const.Queue.TOPIC_WOMAN);
    }

    /**
     * 交换机
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(Const.Exchange.TOPIC_EXCHANGE);
    }


    /**
     * 将队列1与topicExchange绑定,而且绑定的路由值为 top.man
     * 只要消息携带的路由值是 top.man 才会分发到该队列
     *
     * @return
     */
    @Bean
    Binding bindingExchangeMessageMan() {
        return BindingBuilder.bind(manQueue()).to(exchange()).with(Const.Routing.MAN);
    }

    /**
     * 绑定队列与交换机,而且绑定路由规则为 top开头的所有路由键
     *
     * @return
     */
    @Bean
    Binding bindingExchangeMessageWoman() {
        return BindingBuilder.bind(womanQueue()).to(exchange()).with(Const.Rule.WOMAN);
    }


}
