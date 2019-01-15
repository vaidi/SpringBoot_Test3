package com.cn.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/3 16:13
 * @Description:
 */
@Configuration
public class ThreadPoolExecutorConfig {

    @Bean("pool")
    public ThreadPoolExecutor getThreadPool(){
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor pool  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1,
                10L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10000));
        return pool;
    }


}
