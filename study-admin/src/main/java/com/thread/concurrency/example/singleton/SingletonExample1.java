package com.thread.concurrency.example.singleton;

import com.thread.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 */
@ThreadSafe
public class SingletonExample1 {

    private static volatile SingletonExample1 instance=null;

    private SingletonExample1() {

    }

    public static SingletonExample1 getInstance(){

        if(instance==null){
            synchronized (SingletonExample1.class){
                if(instance==null){
                    instance=new SingletonExample1();
                }
            }
        }

        return instance;
    }
}
