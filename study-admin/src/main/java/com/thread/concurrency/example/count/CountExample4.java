package com.thread.concurrency.example.count;

import com.thread.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class CountExample4 {
    public static int clientTotal=5000;

    public static int threadTotal=200;

    public static volatile int count=0;


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
        count++;
    }
}
