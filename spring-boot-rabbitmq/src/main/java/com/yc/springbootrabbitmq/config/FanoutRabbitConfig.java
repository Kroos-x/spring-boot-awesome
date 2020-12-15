package com.yc.springbootrabbitmq.config;

import com.yc.springbootrabbitmq.common.Const;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: 扇形交换机
 *
 * @Author: xieyc
 * @Date: 2020-12-13
 */
@Configuration
public class FanoutRabbitConfig {

    /**
     * 创建三个队列,并分别绑定到交换机上
     * 扇形交换机,路由键无需配置
     */

    /**
     * 队列A
     *
     * @return
     */
    @Bean
    public Queue queueA() {
        return new Queue(Const.Queue.FANOUT_QUEUE_A);
    }

    /**
     * 队列B
     *
     * @return
     */
    @Bean
    public Queue queueB() {
        return new Queue(Const.Queue.FANOUT_QUEUE_B);
    }

    /**
     * 队列C
     *
     * @return
     */
    @Bean
    public Queue queueC() {
        return new Queue(Const.Queue.FANOUT_QUEUE_C);
    }

    /**
     * 指定交换机
     *
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(Const.Exchange.FANOUT_EXCHANGE);
    }

    /**
     * queue bind exchange
     */
    @Bean
    Binding bindingQueueA() {
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    /**
     * queue bind exchange
     */
    @Bean
    Binding bindingQueueB() {
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    /**
     * queue bind exchange
     */
    @Bean
    Binding bindingQueueC() {
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }

}
