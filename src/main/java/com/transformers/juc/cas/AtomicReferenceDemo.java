package com.transformers.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        // 这里如果不使用变量，而是直接使用常量数字，并且不在[-128,127]这个区间内，会被认为不是一个对象，而出现无法更新的问题。
        Integer i128 = 128;
        Integer i129 = 129;
        Integer i256 = 256;
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(i128, 1);

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " stamp = " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + atomicStampedReference.compareAndSet(i128, i129, stamp, stamp + 1));
            stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " stamp = " + stamp);
            System.out.println(Thread.currentThread().getName() + atomicStampedReference.getReference());

            System.out.println(Thread.currentThread().getName() + atomicStampedReference.compareAndSet(i129, i128, stamp, stamp + 1));
            stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " stamp = " + stamp);
            System.out.println(Thread.currentThread().getName() + atomicStampedReference.getReference());

        }, "a").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " stamp = " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + atomicStampedReference.compareAndSet(i128, i256, stamp, stamp + 1));
            stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " stamp = " + stamp);
            System.out.println(Thread.currentThread().getName() + atomicStampedReference.getReference());

        }, "b").start();

        /**
         * 2种结果
         * 线程a成功：
         * a stamp = 1
         * b stamp = 1
         * atrue
         * bfalse
         * a stamp = 2
         * b stamp = 2
         * b129
         * a129
         * atrue
         * a stamp = 3
         * a128
         *
         * 线程b成功：
         * a stamp = 1
         * b stamp = 1
         * btrue
         * afalse
         * b stamp = 2
         * a stamp = 2
         * b256
         * a256
         * afalse
         * a stamp = 2
         * a256
         */
    }
}
