package com.cn.service.testDisruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/3 22:03
 * @Description:
 */
public class DisruptorUtil {


    public static void main(String[] args) {
        produce();
    }



    public static void produce(){
        Long value =1L;
        int ringBufferSize = 1024;//必须是2的N次方
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(new LongEventFactory(),ringBufferSize,
                new LongThreadFactory(), ProducerType.SINGLE,new BlockingWaitStrategy());
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.setDefaultExceptionHandler(new LongExceptionHandler());
        RingBuffer<LongEvent> ringBuffer = disruptor.start();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        producer.produce(value);

//        EventFactory<LongEvent> eventFactory = new LongEventFactory();
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        int ringBufferSize = 1024 * 1024; // RingBuffer 大小，必须是 2 的 N 次方；
//        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory,
//                ringBufferSize, executor, ProducerType.SINGLE,
//                new YieldingWaitStrategy());
//        EventHandler<LongEvent> eventHandler = new LongEventHandler();
//        disruptor.handleEventsWith(eventHandler);
//        disruptor.start();
        System.out.println("执行到这里啦");
    }



}
