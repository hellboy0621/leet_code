package com.transformers.advanced.week04.class07.lock.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Count {

    private final ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        try {
            try {
                System.out.println(Thread.currentThread().getName() + " -> get start");
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println(Thread.currentThread().getName() + " -> get end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public void put() {
        // 打开下一行代码，put方法就相当于无锁状态，因为每次调用put方法，都会新生成一个锁
        // final ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            try {
                System.out.println(Thread.currentThread().getName() + " -> put start");
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println(Thread.currentThread().getName() + " -> put end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }
}
