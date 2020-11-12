package com.transformers.advanced.week04.class07.lock.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class LockCounter {

    public static void main(String[] args) {
        int loop = 1000_000;
        LockCounter counter = new LockCounter();
        IntStream.range(0, loop).parallel().forEach(i -> {
            counter.incrAndGet();
        });
        System.out.println(counter.getNum());
    }

    // 可重入锁+公平锁
    private Lock lock = new ReentrantLock(true);
    private int num = 0;

    public int incrAndGet() {
        lock.lock();
        try {
            return ++num;
        } finally {
            lock.unlock();
        }
    }

    public int getNum() {
        return num;
    }
}
