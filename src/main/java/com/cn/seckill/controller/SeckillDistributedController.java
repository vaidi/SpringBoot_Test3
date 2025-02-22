package com.cn.seckill.controller;

import com.cn.seckill.acitvemq.ActiveMQSender;
import com.cn.seckill.kafka.KafkaSender;
import com.cn.seckill.model.Result;
import com.cn.seckill.redis.RedisSender;
import com.cn.seckill.service.ISeckillDistributedService;
import com.cn.seckill.service.ISeckillService;
import com.cn.seckill.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/7 10:44
 * @Description:
 */
@Api(tags ="分布式秒杀")
@RestController
@RequestMapping("/seckillDistributed")
public class SeckillDistributedController {

    private static Logger LOGGER = LoggerFactory.getLogger(SeckillDistributedController.class);
    private static int corePooleSize = Runtime.getRuntime().availableProcessors();
    private static ThreadPoolExecutor executor =new ThreadPoolExecutor(corePooleSize,corePooleSize+1,1,
            TimeUnit.SECONDS,new LinkedBlockingDeque<>(10000));
    @Autowired
    private ISeckillService seckillService;
    @Autowired
    private ISeckillDistributedService seckillDistributedService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisSender redisSender;
    @Autowired
    private KafkaSender kafkaSender;
    @Autowired
    private ActiveMQSender activeMQSender;

    @ApiOperation(value="秒杀一(Rediss分布式锁)",nickname="科帮网")
    @PostMapping("/startRedisLock")
    public Result startRedisLock(long seckillId){
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀一");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    Result result = seckillDistributedService.startSeckilRedisLock(killId, userId);
                    LOGGER.info("用户:{}{}",userId,result.get("msg"));
                }
            };
            executor.execute(task);
        }
        try {
            sleep(15000);
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    @ApiOperation(value="秒杀二(zookeeper分布式锁)",nickname="科帮网")
    @PostMapping("/startZkLock")
    public Result startZkLock(long seckillId) throws InterruptedException {
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀二");
        sleep(5000);
        for(int i=0;i<10000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    Result result = seckillDistributedService.startSeckilZksLock(killId, userId);
                    LOGGER.info("用户:{}{}",userId,result.get("msg"));
                }
            };
            executor.execute(task);
        }
        try {
            sleep(10000);
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    @ApiOperation(value="秒杀三(Redis分布式队列-订阅监听)",nickname="科帮网")
    @PostMapping("/startRedisQueue")
    public Result startRedisQueue(long seckillId) throws InterruptedException {
        redisUtil.cacheValue(seckillId+"", null);//秒杀结束
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀三");
        sleep(10000);
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    if(redisUtil.getValue(killId+"")==null){
                        //思考如何返回给用户信息ws
                        redisSender.sendChannelMess("seckill",killId+";"+userId);
                    }else{
                        //秒杀结束
                    }
                }
            };
            executor.execute(task);
        }
        try {
            Thread.sleep(10000);
            redisUtil.cacheValue(killId+"", null);
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }


    @ApiOperation(value="秒杀四(Kafka分布式队列)",nickname="科帮网")
    @PostMapping("/startKafkaQueue")
    public Result startKafkaQueue(long seckillId){
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀四");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    if(redisUtil.getValue(killId+"")==null){
                        //思考如何返回给用户信息ws
                        kafkaSender.sendChannelMess("seckill",killId+";"+userId);
                    }else{
                        //秒杀结束
                    }
                }
            };
            executor.execute(task);
        }
        try {
            Thread.sleep(10000);
            redisUtil.cacheValue(killId+"", null);
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }



    @ApiOperation(value="秒杀五(ActiveMQ分布式队列)",nickname="科帮网")
    @PostMapping("/startActiveMQQueue")
    public Result startActiveMQQueue(long seckillId){
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀五");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    if(redisUtil.getValue(killId+"")==null){
                        Destination destination = new ActiveMQQueue("seckill.queue");
                        //思考如何返回给用户信息ws
                        activeMQSender.sendChannelMess(destination,killId+";"+userId);
                    }else{
                        //秒杀结束
                    }
                }
            };
            executor.execute(task);
        }
        try {
            Thread.sleep(10000);
            redisUtil.cacheValue(killId+"", null);
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

}
