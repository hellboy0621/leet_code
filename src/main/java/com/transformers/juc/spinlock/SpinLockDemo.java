package com.transformers.juc.spinlock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    // 加锁
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " SpinLockDemo.myLock");
        // 自旋锁
        while (!atomicReference.compareAndSet(null, thread)) {
        }
    }
    // 解锁
    public void myUnlock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " SpinLockDemo.myUnlock");
        atomicReference.compareAndSet(thread, null);
    }
}
