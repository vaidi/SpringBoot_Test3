package com.cn.seckill.util;


import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁帮助类
 * @author 科帮网 By https://blog.52itstyle.com
 */
@Component
public class RedissLockUtil {

    @Autowired
    private  RedissonClient redissonClient;
    
    public void setRedissonClient(RedissonClient locker) {
    	redissonClient = locker;
    }
    
    /**
     * 加锁
     * @param lockKey
     * @return
     */
    public  RLock lock(String lockKey) {
    	RLock lock = redissonClient.getLock(lockKey);
    	lock.lock();
        return lock;
    }

    /**
     * 释放锁
     * @param lockKey
     */
    public  void unlock(String lockKey) {
    	RLock lock = redissonClient.getLock(lockKey);
		lock.unlock();
    }
    
    /**
     * 释放锁
     * @param lock
     */
    public  void unlock(RLock lock) {
    	lock.unlock();
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    public  RLock lock(String lockKey, int timeout) {
    	RLock lock = redissonClient.getLock(lockKey);
		lock.lock(timeout, TimeUnit.SECONDS);
		return lock;
    }
    
    /**
     * 带超时的锁
     * @param lockKey
     * @param unit 时间单位
     * @param timeout 超时时间
     */
    public  RLock lock(String lockKey, TimeUnit unit ,int timeout) {
    	RLock lock = redissonClient.getLock(lockKey);
		lock.lock(timeout, unit);
		return lock;
    }
    
    /**
     * 尝试获取锁
     * @param lockKey
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public  boolean tryLock(String lockKey, int waitTime, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
		try {
			return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			return false;
		}
    }
    
    /**
     * 尝试获取锁
     * @param lockKey
     * @param unit 时间单位
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public  boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
    	RLock lock = redissonClient.getLock(lockKey);
		try {
			return lock.tryLock(waitTime, leaseTime, unit);
		} catch (InterruptedException e) {
			return false;
		}
    }
}