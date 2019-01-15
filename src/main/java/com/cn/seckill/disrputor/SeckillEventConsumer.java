package com.cn.seckill.disrputor;

import com.cn.seckill.service.ISeckillService;
import com.cn.seckill.util.SpringUtil;
import com.lmax.disruptor.EventHandler;

/**
 * 消费者(秒杀处理器)
 * 创建者 科帮网
 */
public class SeckillEventConsumer implements EventHandler<SeckillEvent> {
	
	//private ISeckillService seckillService = (ISeckillService) SpringUtil.getBean("seckillService");
	private ISeckillService seckillService = (ISeckillService) SpringUtil.getBean("seckillService");

	@Override
	public void onEvent(SeckillEvent seckillEvent, long seq, boolean bool) throws Exception {
		seckillService.startSeckil(seckillEvent.getSeckillId(), seckillEvent.getUserId());
	}
}
