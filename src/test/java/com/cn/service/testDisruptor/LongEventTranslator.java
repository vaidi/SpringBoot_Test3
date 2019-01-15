package com.cn.service.testDisruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/4 14:18
 * @Description:
 */
public class LongEventTranslator implements EventTranslatorOneArg<LongEvent,Long> {
    @Override
    public void translateTo(LongEvent event, long sequence, Long arg0) {
        System.out.println("转化消息前"+event.getValue()+",arg0:"+arg0);
        event.setValue(arg0);
        System.out.println("转化消息后："+event.getValue()+",arg0:"+arg0);
    }
}
