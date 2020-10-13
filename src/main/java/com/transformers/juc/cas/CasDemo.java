package com.transformers.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2020, 2022));
        System.out.println(atomicInteger.get());
        atomicInteger.getAndIncrement();
    }
    /*
     * true
     * 2021
     * false
     * 2021
     */
}
