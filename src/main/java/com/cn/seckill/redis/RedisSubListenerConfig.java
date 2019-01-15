package com.cn.seckill.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisSubListenerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSubListenerConfig.class);
    //初始化监听器
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        LOGGER.info("初始化监听器");
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("seckill"));
        return container;
    }
    //利用反射来创建监听到消息之后的执行方法
    @Bean
    MessageListenerAdapter listenerAdapter(RedisConsumer redisReceiver) {
        LOGGER.info("利用反射来创建监听到消息之后的执行方法");
        return new MessageListenerAdapter(redisReceiver, "receiveMessage");
    }
   //使用默认的工厂初始化redis操作模板
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        LOGGER.info("使用默认的工厂方法初始化redis操作模板");
        return new StringRedisTemplate(connectionFactory);
    }
}