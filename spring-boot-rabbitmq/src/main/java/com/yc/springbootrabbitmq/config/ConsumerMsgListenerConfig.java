package com.yc.springbootrabbitmq.config;

import com.yc.springbootrabbitmq.common.Const;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: 消费者消息确认配置
 *
 * @Author: xieyc
 * @Date: 2020-12-14
 */
@Configuration
public class ConsumerMsgListenerConfig {

    /**
     * 消息接受处理类
     */
    private final MyAckConsumerConfig myAckConsumerConfig;
    private final CachingConnectionFactory connectionFactory;

    public ConsumerMsgListenerConfig(CachingConnectionFactory connectionFactory, MyAckConsumerConfig myAckConsumerConfig) {
        this.connectionFactory = connectionFactory;
        this.myAckConsumerConfig = myAckConsumerConfig;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // RabbitMQ 默认是自动确认消息,改为手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置一个队列
        container.setQueueNames(Const.Queue.DIRECT_QUEUE);
        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
        //  container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");

        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        //container.setQueues(new Queue("TestDirectQueue",true));
        //container.addQueues(new Queue("TestDirectQueue2",true));
        //container.addQueues(new Queue("TestDirectQueue3",true));
        container.setMessageListener(myAckConsumerConfig);
        return container;

    }

    /**
     * 消息监听的确认机制存在三种模式
     * 1.自动确认(默认的消息确认, AcknowledgeMode.NONE)
     *      RabbitMQ 成功将消息发出后立即认为本次投递已经被正确处理,不管消费者是否成功处理本次投递
     *      所以此情况如果消费端没有成功处理这条消息,就相当于丢失了消息。
     * 2.根据情况确认
     * 3.手动确认(多用,消费者收到消息后,手动调用basic.ack/basic.nack/basic.reject)后,RabbitMQ收到这些反馈后,才认为本次投递成功
     *      3.1 basic.ack 用于肯定确认
     *      3.2 basic.nack 用于否定确认
     *      3.3 basic.reject 用于否定确认,与basic.nack相比,一次只能拒绝单挑消息
     *    以上三个方法都表示消息被正确投递,但是basic.ack 表示消息已经被正确处理,而basic.nack,basoc.reject表示没有被正确处理
     *
     *    注:reject
     *      channel.basicReject(deliveryTag,true): 拒绝消费当前消息,如果第二参数传入true,意味着将消息丢回队列里,下次还会消费这条消息
     *      channel.basicReject(deliveryTag,false): 通知服务器,已收到这条数据,因为一些原因拒绝它,而且服务器也把这个消息丢掉就行
     *
     *    注: nack
     *      channel.basicNack(deliveryTag,false,true);
     *      param1:当前消息的数据的唯一ID
     *      param2:是否针对多条消息;如果是true,意味着一次性针对当前通道的消息的tagID 小于当前这条消息的,都拒绝确认
     *      param3:是否重新入列,也就是指不确认的消息是否重新丢回到队列里面(重新丢回需要考虑消息重复积压的问题)
     *
     */
}
