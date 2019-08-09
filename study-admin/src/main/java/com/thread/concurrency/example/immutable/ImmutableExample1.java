package com.thread.concurrency.example.immutable;



import com.google.common.collect.Maps;
import com.thread.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a=1;
    private final static String b="2";
    private final static Map<Integer,Integer> map= Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(2,3);
    }

    public static void main(String[] args) {
        map.put(1,5);
        log.info("{}",map.get(1));
    }

    private void test1(final int a){
        //a=1;
    }
}
