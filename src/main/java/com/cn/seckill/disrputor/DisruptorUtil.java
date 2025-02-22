package com.cn.seckill.disrputor;


import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.ThreadFactory;

/**
 * 来自<tukangzheng>的建议，具体性能待测试
 * 创建者 张志朋
 * 创建时间	2018年5月23日
 *
 */
public class DisruptorUtil {
	
	static Disruptor<SeckillEvent> disruptor = null;
	static{
		SeckillEventFactory factory = new SeckillEventFactory();
		int ringBufferSize = 1024;
		ThreadFactory threadFactory = new ThreadFactory() {
			@Override
			public Thread newThread(Runnable runnable) {
				return new Thread(runnable);
			}
		};
		disruptor = new Disruptor<SeckillEvent>(factory, ringBufferSize, threadFactory);
		disruptor.handleEventsWith(new SeckillEventConsumer());
		disruptor.start();
	}
	
	public static void producer(SeckillEvent kill){
		RingBuffer<SeckillEvent> ringBuffer = disruptor.getRingBuffer();
		SeckillEventProducer producer = new SeckillEventProducer(ringBuffer);
		producer.seckill(kill.getSeckillId(),kill.getUserId());
	}
}
