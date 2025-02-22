package com.cn.service.redissonTest;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/7 14:20
 * @Description:
 */
public class RedissonTest {


    public static void main(String[] args) {
        boolean flag =DistributedRedisLock.acquire("yuanerlong");
        System.out.println("flag:"+flag);
        if(flag){
            System.out.println("当前线程被锁住"+flag);
        }

    }



    static class DistributedRedisLock{
        //从配置类中获取redisson对象
        private static Redisson redisson = RedissonManager.getRedisson();
        private static final String LOCK_TITLE = "redisLock_";
        //加锁
        public static boolean acquire(String lockName){
            //声明key对象
            String key = LOCK_TITLE + lockName;
            //获取锁对象
            RLock mylock = redisson.getLock(key);
            //加锁，并且设置锁过期时间，防止死锁的产生
            mylock.lock(2, TimeUnit.MINUTES);
            System.err.println("======lock======"+Thread.currentThread().getName());
            //加锁成功
            return  true;
        }
        //锁的释放
        public static void release(String lockName){
            //必须是和加锁时的同一个key
            String key = LOCK_TITLE + lockName;
            //获取所对象
            RLock mylock = redisson.getLock(key);
            //释放锁（解锁）
            mylock.unlock();
            System.err.println("======unlock======"+Thread.currentThread().getName());
        }

    }


    static class RedissonManager{
        private static Config config = new Config();
        //声明redisso对象
        private static Redisson redisson = null;
        //实例化redisson
        static{
            config.useSingleServer().setAddress("redis://10.1.11.110:6390");
            config.useSingleServer().setPassword("6390");
            // config.useSingleServer().setAddress("redis://10.1.128.175:54594");
            //config.useSingleServer().setPassword("6379");
            //得到redisson对象
            redisson = (Redisson) Redisson.create(config);
            System.out.println("redisson hashCode:"+redisson.hashCode());
        }

        //获取redisson对象的方法
        public static Redisson getRedisson(){
            return redisson;
        }
    }














}
