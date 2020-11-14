package com.transformers.advanced.week04.class08;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreCounter {

    public static void main(String[] args) {
        SemaphoreCounter counter = new SemaphoreCounter();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    counter.incrAndGet();
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getNum());
    }

    private int num;
    private Semaphore readSemaphore = new Semaphore(100, true);
    private Semaphore writeSemaphore = new Semaphore(1);

    public int incrAndGet() {
        writeSemaphore.acquireUninterruptibly();
        try {
            return ++num;
        } finally {
            writeSemaphore.release();
        }
    }

    public int getNum() {
        readSemaphore.acquireUninterruptibly();
        try {
            return num;
        } finally {
            readSemaphore.release();
        }
    }
}
