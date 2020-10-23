package com.yc.redis.module;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 监听器实现
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-08-20
 */
@Component
public class ListenerModule extends KeyExpirationEventMessageListener {

    /**
     * 配置监听哪个频道
     * keyevent: 监听的事件类型
     * 格式为固定，Redis有16个库，配置中0代表监听第0个库，如果要监听所有库，可将0改为*，星号，如果监听其他库，将0改为库的编号即可0-15。
     * expired: 监听的时间为过期事件.注意，配置中有两处出现了下划线，每一处下划线均有两个下划线组成
     */
    private static final Topic KEYEVENT_EXPIRED_TOPIC = new PatternTopic("__keyevent@0__:expired");

    public ListenerModule(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    protected void doRegister(RedisMessageListenerContainer listenerContainer) {
        // 频道可以是多，多个传list
        listenerContainer.addMessageListener(this, KEYEVENT_EXPIRED_TOPIC);
    }

    /**
     * 监听消息
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        System.out.println("失效的key为:" + expiredKey);
    }
}
