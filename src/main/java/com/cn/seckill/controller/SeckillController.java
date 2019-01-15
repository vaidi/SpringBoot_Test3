package com.cn.seckill.controller;

import com.cn.seckill.disrputor.DisruptorUtil;
import com.cn.seckill.disrputor.SeckillEvent;
import com.cn.seckill.model.Result;
import com.cn.seckill.model.SuccessKilled;
import com.cn.seckill.queue.jvm.SeckillQueue;
import com.cn.seckill.service.ISeckillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @Auther: YUANEL
 * @Date: 2019/1/3 16:06
 * @Description:
 */
@Api(tags ="秒杀")
@RestController
@RequestMapping("/seckill")
public class SeckillController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeckillController.class);

    @Autowired
    private ThreadPoolExecutor pool;
    @Autowired
    private ISeckillService seckillService;


    @ApiOperation(value="秒杀一(最low实现)",nickname="科帮网")
    @PostMapping("/start")
    public Result start(long seckillId){
        int skillNum = 10000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀一(会出现超卖)");
        for(int i=0;i<skillNum;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    Result result = seckillService.startSeckil(killId, userId);
                    if(result!=null){
                        LOGGER.info("用户:{}{}",userId,result.get("msg"));
                    }else{
                        LOGGER.info("用户:{}{}",userId,"哎呦喂，人也太多了，请稍后！");
                    }
                    latch.countDown();
                }
            };
            pool.execute(task);
        }
        try {
            latch.await();// 等待所有人任务结束
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }


    @ApiOperation(value="秒杀二(程序锁)",nickname="科帮网")
    @PostMapping("/startLock")
    public Result startLock(long seckillId){
        int skillNum = 10000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
         Lock lock = new ReentrantLock(true);//互斥锁 参数默认false，不公平锁
        LOGGER.info("开始秒杀二(正常)");
        for(int i=0;i<skillNum;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    Result result = seckillService.startSeckilLock(killId, userId,lock);
                    LOGGER.info("用户:{}{}",userId,result.get("msg"));
                    latch.countDown();
                }
            };
            pool.execute(task);
        }
        try {
            latch.await();// 等待所有人任务结束
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    @ApiOperation(value="秒杀三(AOP程序锁)",nickname="科帮网")
    @PostMapping("/startAopLock")
    public Result startAopLock(long seckillId){
        int skillNum = 10000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀三(正常)");
        for(int i=0;i<skillNum;i++){
            final long userId = i;
          //  Result result = seckillService.startSeckilAopLock(killId, userId);
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    Result result = seckillService.startSeckilAopLock(killId, userId);
                    LOGGER.info("用户:{}{}",userId,result.get("msg"));
                    latch.countDown();
                }
            };
            pool.execute(task);
        }
        try {
            latch.await();// 等待所有人任务结束
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    @ApiOperation(value="秒杀四(数据库悲观锁)",nickname="科帮网")
    @PostMapping("/startDBPCC_ONE")
    public Result startDBPCC_ONE(long seckillId){
        int skillNum = 1000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀四(正常)");
        for(int i=0;i<skillNum;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    Result result = seckillService.startSeckilDBPCC_ONE(killId, userId);
                    LOGGER.info("用户:{}{}",userId,result.get("msg"));
                    latch.countDown();
                }
            };
            pool.execute(task);
        }
        try {
            latch.await();// 等待所有人任务结束
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    @ApiOperation(value="秒杀五(数据库悲观锁)",nickname="科帮网")
    @PostMapping("/startDPCC_TWO")
    public Result startDPCC_TWO(long seckillId){
        int skillNum = 1000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀五(正常、数据库锁最优实现)");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    Result result = seckillService.startSeckilDBPCC_TWO(killId, userId);
                    LOGGER.info("用户:{}{}",userId,result.get("msg"));
                    latch.countDown();
                }
            };
            pool.execute(task);
        }
        try {
            latch.await();// 等待所有人任务结束
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }



    @ApiOperation(value="秒杀六(数据库乐观锁)",nickname="科帮网")
    @PostMapping("/startDBOCC")
    public Result startDBOCC(long seckillId){
        int skillNum = 10000;
        final CountDownLatch latch = new CountDownLatch(skillNum);//N个购买者
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀六(正常、数据库锁最优实现)");
        for(int i=0;i<skillNum;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    //这里使用的乐观锁、可以自定义抢购数量、如果配置的抢购人数比较少、比如120:100(人数:商品) 会出现少买的情况
                    //用户同时进入会出现更新失败的情况
                    Result result = seckillService.startSeckilDBOCC(killId, userId,1);
                    LOGGER.info("用户:{}{}",userId,result.get("msg"));
                    latch.countDown();
                }
            };
            pool.execute(task);
        }
        try {
            latch.await();// 等待所有人任务结束
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }


    @ApiOperation(value="秒杀柒(进程内队列)",nickname="科帮网")
    @PostMapping("/startQueue")
    public Result startQueue(long seckillId){
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀柒(正常)");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    SuccessKilled kill = new SuccessKilled();
                    kill.setSeckillId(killId);
                    kill.setUserId(userId);
                    try {
                        Boolean flag = SeckillQueue.getMailQueue().produce(kill);
                        if(flag){
                            LOGGER.info("用户:{}{}",kill.getUserId(),"秒杀成功");
                        }else{
                            LOGGER.info("用户:{}{}",userId,"秒杀失败");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        LOGGER.info("用户:{}{}",userId,"秒杀失败");
                    }
                }
            };
            pool.execute(task);
        }
        try {
            Thread.sleep(10000);
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }


    @ApiOperation(value="秒杀捌(Disruptor队列)",nickname="科帮网")
    @PostMapping("/startDisruptorQueue")
    public Result startDisruptorQueue(long seckillId){
        seckillService.deleteSeckill(seckillId);
        final long killId =  seckillId;
        LOGGER.info("开始秒杀八(正常)");
        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    SeckillEvent kill = new SeckillEvent();
                    kill.setSeckillId(killId);
                    kill.setUserId(userId);
                    DisruptorUtil.producer(kill);
                }
            };
            pool.execute(task);
        }
        try {
            Thread.sleep(50000);
            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            LOGGER.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

}
