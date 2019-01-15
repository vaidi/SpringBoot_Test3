package com.cn.seckill.kafka;

import com.cn.seckill.model.Result;
import com.cn.seckill.service.ISeckillService;
import com.cn.seckill.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/8 15:42
 * @Description:
 */
@Component
public class KafkaConsumer {

    @Autowired
    private ISeckillService seckillService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 监听seckill主题,有消息就读取
     *
     * @param message
     */
    @KafkaListener(topics = {"seckill"})
    public void receiveMessage(String message) throws InterruptedException {
        //收到通道的消息之后执行秒杀操作
        String[] array = message.split(";");
        System.out.println("kafka消息队列：" + array[0] + ",,,," + array[1]);
        //sleep(10);
        //if(redisUtil.getValue(array[0])==null){//control层已经判断了，其实这里不需要再判断了，这个接口有限流 注意一下
        Result result = seckillService.startSeckil(Long.parseLong(array[0]), Long.parseLong(array[1]));
        //可以注释掉上面的使用这个测试
        //Result result = seckillService.startSeckilDBPCC_TWO(Long.parseLong(array[0]), Long.parseLong(array[1]));
        System.out.println("##################result:" + result);
        if (result.equals(Result.ok())) {
            // WebSocketServer.sendInfo(array[0].toString(), "秒杀成功");//推送给前台
        } else {
            //  WebSocketServer.sendInfo(array[0].toString(), "秒杀失败");//推送给前台
            redisUtil.cacheValue(array[0], "ok");//秒杀结束
        }
//        }else{
//          //  WebSocketServer.sendInfo(array[0].toString(), "秒杀失败");//推送给前台
//        }
    }
}
