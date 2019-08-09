package com.thread.concurrency.example.immutable;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.thread.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList<Integer> list=ImmutableList.of(1,2,3);
    private final static ImmutableSet set=ImmutableSet.copyOf(list);
    private final static ImmutableMap<Integer,Integer> map=ImmutableMap.<Integer,Integer>builder().put(1,1)
            .put(2,2).put(3,3).build();

    public static void main(String[] args) {
        //map.add(1,2);
        //map.add(3);
        //map.put(1,2);
        log.info("{}",map.get(2));
    }
}
