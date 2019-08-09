package com.thread.concurrency.example.lock;

import com.thread.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

@Slf4j
@ThreadSafe
public class LockExample5 {
    public static int clientTotal=5000;

    public static int threadTotal=200;

    public static int count=0;

    private final static StampedLock lock=new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService= Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore=new Semaphore((threadTotal));
        //计数器闭锁
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);

        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);


    }

    private static void add() {
        long stamp=lock.writeLock();
        try{
            count++;
        }finally {
            lock.unlock(stamp);
        }

    }
}
