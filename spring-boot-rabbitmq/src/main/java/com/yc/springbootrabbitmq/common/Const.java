package com.yc.springbootrabbitmq.common;

/**
 * 功能描述:
 *
 * @Author: xieyc
 * @Date: 2020-12-11
 */
public class Const {

    /**
     * 交换机
     */
    public static final class Exchange {
        // 直连交换机
        public static final String DIRECT_EXCHANGE = "rabbitmq.direct.exchange";
        // 主题交换机
        public static final String TOPIC_EXCHANGE = "rabbitmq.topic.exchange";
        // 扇形交换机
        public static final String FANOUT_EXCHANGE = "rabbitmq.fanout.exchange";
        // 延迟交换机
        public static final String DELAY_EXCHANGE = "rabbitmq.delay.exchange";
    }

    /**
     * 队列
     */
    public static final class Queue {
        // 直连队列
        public static final String DIRECT_QUEUE = "rabbitmq.direct.queue";
        // topic 队列
        public static final String TOPIC_MAN = "topic.man";
        // topic 队列
        public static final String TOPIC_WOMAN = "topic.woman";
        // fanout 队列
        public static final String FANOUT_QUEUE_A = "fanout.queue.a";
        // fanout 队列
        public static final String FANOUT_QUEUE_B = "fanout.queue.b";
        // fanout 队列
        public static final String FANOUT_QUEUE_C = "fanout.queue.c";
        // delay 队列
        public static final String DELAY_QUEUE = "delay.queue";

    }

    /**
     * 路由
     */
    public static final class Routing {
        // 直连路由
        public static final String DIRECT_ROUTING = "rabbitmq.direct.routing";
        // topic 路由
        public static final String MAN = "topic.man";
        // topic 路由
        public static final String WOMAN = "topic.woman";
        // 延迟 路由
        public static final String DELAY = "rabbitmq.delay";
    }


    /**
     * 规则
     */
    public static final class Rule {
        // 匹配topic开头的所有路由
        public static final String WOMAN = "topic.#";
    }
}
