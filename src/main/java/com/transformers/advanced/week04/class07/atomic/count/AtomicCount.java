package com.transformers.advanced.week04.class07.atomic.count;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCount implements ICount {

    private AtomicInteger num = new AtomicInteger(0);

    @Override
    public void add() {
        num.incrementAndGet();
    }

    @Override
    public int get() {
        return num.get();
    }
}
