package com.yc.springbootrabbitmq.config;

import com.yc.springbootrabbitmq.common.Const;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: 延迟消息队列配置
 *
 * @Author: xieyc
 * @Date: 2020-12-15
 */
@Configuration
public class DelayRabbitConfig {

    /**
     * 注:
     * 延迟消息最大时间4294967295毫秒(49.71天)
     */
    /**
     * 延迟队列
     *
     * @return 队列
     */
    @Bean
    public Queue directQueue() {
        return new Queue(Const.Queue.DELAY_QUEUE, true);
    }

    /**
     * 交换机
     *
     * @return 交换机
     */
    @Bean
    DirectExchange directExchange() {
        DirectExchange exchange = new DirectExchange(Const.Exchange.DELAY_EXCHANGE, true, false);
        exchange.setDelayed(true);
        return exchange;
    }

    /**
     * 绑定队列与交换机,并设置匹配路由
     *
     * @return 绑定
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(Const.Routing.DELAY);
    }

}
