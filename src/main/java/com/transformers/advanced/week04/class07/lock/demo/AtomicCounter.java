package com.transformers.advanced.week04.class07.lock.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private AtomicInteger num = new AtomicInteger(0);

    public int incrAndGet() {
        return num.incrementAndGet();
    }

    public int getNum() {
        return num.get();
    }
}
