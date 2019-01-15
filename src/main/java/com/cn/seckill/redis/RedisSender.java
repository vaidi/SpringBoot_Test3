package com.cn.seckill.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 生产者
 * @author 科帮网 By https://blog.52itstyle.com
 */
@Service
public class RedisSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSender.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //向通道发送消息的方法
    public void sendChannelMess(String channel, String message) {
        LOGGER.info("向通道发送消息");
        stringRedisTemplate.convertAndSend(channel, message);
    }
}
