package com.cn.service.testDisruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/4 14:25
 * @Description:
 */
public class LongEventProducer {

    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    /**
     * 将接受到的消息发布到ringbuffer上去
     */
    public void produce(Long value){
        EventTranslatorOneArg<LongEvent,Long> translator = new LongEventTranslator();
        ringBuffer.publishEvent(translator,value);
    }



}
