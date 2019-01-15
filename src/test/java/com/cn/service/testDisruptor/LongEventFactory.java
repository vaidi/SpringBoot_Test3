package com.cn.service.testDisruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/3 21:55
 * @Description:
 * 定义事件工厂
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
