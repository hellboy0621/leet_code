package com.transformers.advanced.week04.class07.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {
    public static void main(String[] args) throws InterruptedException {
        final AtomicLong atomicLong = new AtomicLong();
        final LongAdder longAdder = new LongAdder();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    atomicLong.getAndIncrement();
                    longAdder.increment();
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println("atomicLong -> " + atomicLong.get());
        System.out.println("longAdder  -> " + longAdder.sum());
    }
}
