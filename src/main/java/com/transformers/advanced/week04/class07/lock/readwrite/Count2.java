package com.transformers.advanced.week04.class07.lock.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Count2 {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void get() {
        rwLock.readLock().lock();
        try {
            try {
                System.out.println(Thread.currentThread().getName() + " get start");
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println(Thread.currentThread().getName() + " get end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void put() {
        rwLock.writeLock().lock();
        try {
            try {
                System.out.println(Thread.currentThread().getName() + " put start");
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println(Thread.currentThread().getName() + " put end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
