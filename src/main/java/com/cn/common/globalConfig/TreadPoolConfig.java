package com.cn.common.globalConfig;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @Auther: YUANEL
 * @Date: 2018/11/20 15:37
 * @Description:
 */
@Configuration
public class TreadPoolConfig {


    @Bean(value = "consumerQueueThreadPool")
    public ExecutorService buildConsumerQueueThrreadPool(){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("consumer-queue-thread-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(5,5,0L,
                TimeUnit.MICROSECONDS,new ArrayBlockingQueue<Runnable>(5),namedThreadFactory,new ThreadPoolExecutor
                .AbortPolicy());
        return pool;
    }






}
