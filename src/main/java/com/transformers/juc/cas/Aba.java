package com.transformers.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class Aba {
    public static void main(String[] args) {
        int a = 2020;
        int b = 3030;
        int c = 4040;
        AtomicInteger atomicInteger = new AtomicInteger(a);
        // 线程A
        atomicInteger.compareAndSet(a, b);
        atomicInteger.compareAndSet(b, a);
        // 线程B，不能感知到线程A将值从a改为b又改为a。
        atomicInteger.compareAndSet(a, c);
        System.out.println(atomicInteger.get());
    }
}
