package com.transformers.juc.spinlock;

import java.util.concurrent.TimeUnit;

public class TestSpinLock {
    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo lock = new SpinLockDemo();
        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.myUnlock();
            }
        }, "T1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.myUnlock();
            }
        }, "T2").start();
    }
    /**
     * T1 SpinLockDemo.myLock
     * T2 SpinLockDemo.myLock
     * T1 SpinLockDemo.myUnlock
     * T2 SpinLockDemo.myUnlock
     */
}
