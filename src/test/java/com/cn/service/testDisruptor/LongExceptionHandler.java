package com.cn.service.testDisruptor;

import com.lmax.disruptor.ExceptionHandler;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/4 14:24
 * @Description:
 * 异常处理类
 */
public class LongExceptionHandler  implements ExceptionHandler<LongEvent> {

    @Override
    public void handleEventException(Throwable ex, long sequence, LongEvent event) {
        ex.printStackTrace();
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        ex.printStackTrace();
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        ex.printStackTrace();
    }
}
