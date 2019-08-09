package com.thread.concurrency.example.concurrent;

import com.thread.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class ConcurrentSkipListMapExample {
    public static int clientTotal=5000;

    public static int threadTotal=200;

    public static Map<Integer,Integer> map = new ConcurrentSkipListMap<>();


    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService= Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore=new Semaphore((threadTotal));
        //计数器闭锁
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);

        for(int i=0;i<clientTotal;i++){
            final int count=i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", map.size());


    }

    private static void update(int i) {
        map.put(i,i);
    }
}
