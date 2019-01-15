package com.cn.service.runTimeTest;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/3 15:55
 * @Description:
 */
public class RunTimeTest {

    public static void main(String[] args) {
       int corePoolSize = Runtime.getRuntime().availableProcessors();
        System.out.println("corePoolSize:"+corePoolSize);
        //创建线程池  调整队列数 拒绝服务
        ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10l, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1000));
    }



}
