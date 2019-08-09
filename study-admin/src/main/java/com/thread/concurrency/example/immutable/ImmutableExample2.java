package com.thread.concurrency.example.immutable;



import com.google.common.collect.Maps;
import com.thread.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer,Integer> map= Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(2,3);
        map= Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,5);
        log.info("{}",map.get(1));
    }

    private void test1(final int a){
        //a=1;
    }
}
