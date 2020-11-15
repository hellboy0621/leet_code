package com.transformers.advanced.week04.class08.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, AtomicInteger> cache = new ConcurrentHashMap<>();
        CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = () -> {
            AtomicInteger oldValue;
            for (int i = 0; i < 100; i++) {
                oldValue = cache.get("a");
                if (oldValue == null) {
                    AtomicInteger newValue = new AtomicInteger(0);
                    oldValue = cache.putIfAbsent("a", newValue);
                    if (oldValue == null) {
                        oldValue = newValue;
                    }
                }
                oldValue.incrementAndGet();
            }
            endLatch.countDown();
        };
        new Thread(task).start();
        new Thread(task).start();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cache);
    }
    // {a=200}
}
