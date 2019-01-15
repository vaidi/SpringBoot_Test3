package com.cn.service.testDisruptor;


import com.lmax.disruptor.EventHandler;


/**
 * @Auther: YUANEL
 * @Date: 2019/1/3 21:57
 * @Description:
 * 定义事件处理的具体实现
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("事件处理："+event.toString()+",sequence:"+sequence+",endOfBatch:"+endOfBatch);
    }
}
