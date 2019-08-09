package com.thread.concurrency.example.singleton;

import com.thread.concurrency.annoations.Recommend;
import com.thread.concurrency.annoations.ThreadSafe;

/**
 * 枚举模式
 */
@ThreadSafe
@Recommend
public class SingletonExample3 {

    private static SingletonExample3 instance=null;

    private SingletonExample3() {
    }

    public static SingletonExample3 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample3 singleton;

        //JVM保证这个方法绝对只被调用一次
        Singleton(){
            singleton=new SingletonExample3();
        }

        public SingletonExample3 getInstance(){
            return singleton;
        }
    }
}
