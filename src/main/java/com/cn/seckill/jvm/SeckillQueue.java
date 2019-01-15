package com.cn.seckill.jvm;

import com.cn.seckill.model.SuccessKilled;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/8 14:41
 * @Description:
 */
public class SeckillQueue {
    static final int QUEUE_MAX_SIZE =100;
    static   BlockingQueue<SuccessKilled> blockingQueue = new LinkedBlockingDeque<>(QUEUE_MAX_SIZE);

    //私有化构造器
    private SeckillQueue(){}

    private static class SingletonHolder{
        //静态初始化器  由jvm保证线程安全
        private static  SeckillQueue queue  = new SeckillQueue();
    }
    //内部类实现单列
    public static SeckillQueue getSeckillMail(){
        return SingletonHolder.queue;
    }
    /**
     * 生产入队
     * @param kill
     * @throws InterruptedException
     * add(e) 队列未满时，返回true；队列满则抛出IllegalStateException(“Queue full”)异常——AbstractQueue
     * put(e) 队列未满时，直接插入没有返回值；队列满时会阻塞等待，一直等到队列未满时再插入。
     * offer(e) 队列未满时，返回true；队列满时返回false。非阻塞立即返回。
     * offer(e, time, unit) 设定等待的时间，如果在指定时间内还不能往队列中插入数据则返回false，插入成功返回true。
     */
    public  boolean produce(SuccessKilled kill){
      return  blockingQueue.offer(kill);
    }
    /**
     * 消费出队
     * poll() 获取并移除队首元素，在指定的时间内去轮询队列看有没有首元素有则返回，否者超时后返回null
     * take() 与带超时时间的poll类似不同在于take时候如果当前队列空了它会一直等待其他线程调用notEmpty.signal()才会被唤醒
     */
    public  SuccessKilled consume() throws InterruptedException {
        return blockingQueue.take();
    }
    public static int getSize(){
        return blockingQueue.size();
    }




}
