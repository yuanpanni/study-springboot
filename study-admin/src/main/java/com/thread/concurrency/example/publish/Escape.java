package com.thread.concurrency.example.publish;

import com.thread.concurrency.annoations.NotRecommend;
import com.thread.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private  int  thisCanBeEscape=0;

    public Escape(){
        new InnerClass();
    }

    public class InnerClass{
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
