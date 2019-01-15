package com.cn.service.testThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/14 18:43
 * @Description:
 */
//@Slf4j
public class LockExample1 {

    // 请求总数
    public static int clientTotal = 1000;
    // 同时并发执行的线程数
    public static int threadTotal = 100;
    public static int count = 0;
    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println("exception"+ e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
       System.out.println("count:{}"+ count);
    }

    private static void add() {
        lock.lock(); //加锁
        try {
            count++;
        } finally {
           lock.unlock(); //释放锁
        }
    }

}
