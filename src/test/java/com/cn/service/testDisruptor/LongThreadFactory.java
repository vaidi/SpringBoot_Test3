package com.cn.service.testDisruptor;

import java.util.concurrent.ThreadFactory;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/4 14:22
 * @Description:
 */
public class LongThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread("当前处理线程");
    }
}
