package com.thread.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample2 {

    private static int threadCount=20;

    public static void main(String[] args) throws Exception{
        ExecutorService exec= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(3);

        for(int i=0;i<threadCount;i++){
            final int threadNum=i;
            exec.execute(()->{
                try {
                    semaphore.acquire(3);
                    test(threadNum);
                    semaphore.release(3);
                }catch (Exception e){
                    log.info("exception",e);
                }
            });

        }

        log.info("finish");
        exec.shutdown();

    }

    private static void test(int threadNum) throws Exception{

        log.info("{}",threadNum);
        Thread.sleep(1000);

    }
}
