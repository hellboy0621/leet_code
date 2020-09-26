package com.transformers.juc.bq;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put a");
                queue.put("a");
                System.out.println(Thread.currentThread().getName() + " put b");
                queue.put("b");
                System.out.println(Thread.currentThread().getName() + " put c");
                queue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();

    }
}
