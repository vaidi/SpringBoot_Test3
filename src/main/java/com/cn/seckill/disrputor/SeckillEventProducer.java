package com.cn.seckill.disrputor;

import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;

/**
 * 使用translator方式生产者
 * 创建者 科帮网
 */
public class SeckillEventProducer {
	
	private final static EventTranslatorVararg<SeckillEvent> translator = new EventTranslatorVararg<SeckillEvent>() {
		@Override
		public void translateTo(SeckillEvent seckillEvent, long seq, Object... objs) {
			seckillEvent.setSeckillId((Long) objs[0]);
			seckillEvent.setUserId((Long) objs[1]);
		}
	};

	private final RingBuffer<SeckillEvent> ringBuffer;
	
	public SeckillEventProducer(RingBuffer<SeckillEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	public void seckill(long seckillId, long userId){
		this.ringBuffer.publishEvent(translator, seckillId, userId);
	}
}
