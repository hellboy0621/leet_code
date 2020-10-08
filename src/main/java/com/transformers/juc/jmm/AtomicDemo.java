package com.transformers.juc.jmm;

import java.util.concurrent.atomic.AtomicInteger;

// 使用原子类保证原子性
public class AtomicDemo {
    private static AtomicInteger num = new AtomicInteger();
    private static void add() {
        num.getAndIncrement();
    }
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " num = " + num);
    }
}
