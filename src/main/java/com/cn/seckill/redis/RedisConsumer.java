package com.cn.seckill.redis;

import com.cn.seckill.enums.SeckillStatEnum;
import com.cn.seckill.model.Result;
import com.cn.seckill.service.ISeckillService;
import com.cn.seckill.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消费者
 * @author 科帮网 By https://blog.52itstyle.com
 */
@Service
public class RedisConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisConsumer.class);
	
	@Autowired
	private ISeckillService seckillService;
	@Autowired
	private RedisUtil redisUtil;
	
    public void receiveMessage(String message) {
        //收到通道的消息之后执行秒杀操作(超卖)
    	String[] array = message.split(";");
		LOGGER.info("收到通道消息之后执行秒杀操作，message："+message);
    	if(redisUtil.getValue(array[0])==null){//control层已经判断了，其实这里不需要再判断了
    		Result result = seckillService.startSeckilDBPCC_TWO(Long.parseLong(array[0]), Long.parseLong(array[1]));
    		if(result.equals(Result.ok(SeckillStatEnum.SUCCESS))){
    			//WebSocketServer.sendInfo(array[0].toString(), "秒杀成功");//推送给前台
				System.out.println(array[0].toString()+ "秒杀成功");
			}else{
    			//WebSocketServer.sendInfo(array[0].toString(), "秒杀失败");//推送给前台
				System.out.println(array[0].toString()+ "秒杀失败");
				redisUtil.cacheValue(array[0], "ok");//秒杀结束
    		}
    	}else{
    		//WebSocketServer.sendInfo(array[0].toString(), "秒杀失败");//推送给前台
			System.out.println(array[0].toString()+ "秒杀失败");
		}
    }
}