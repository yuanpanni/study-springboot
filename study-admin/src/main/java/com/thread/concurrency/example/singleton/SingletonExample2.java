package com.thread.concurrency.example.singleton;

import com.thread.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 */
@ThreadSafe
public class SingletonExample2 {

    private static SingletonExample2 instance=null;

    static {
        instance=new SingletonExample2();
    }

    private SingletonExample2() {

    }

    public static SingletonExample2 getInstance(){
        return instance;
    }
}
