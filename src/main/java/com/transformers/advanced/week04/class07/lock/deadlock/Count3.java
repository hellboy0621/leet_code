package com.transformers.advanced.week04.class07.lock.deadlock;

import java.util.concurrent.TimeUnit;

public class Count3 {
    private int num = 0;
    private final byte[] lock1 = new byte[1];
    private final byte[] lock2 = new byte[1];

    public void lock1() {
        commonLock(lock1, lock2);
    }

    public void lock2() {
        commonLock(lock2, lock1);
    }

    private void commonLock(final byte[] lock1, final byte[] lock2) {
        synchronized (lock1) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                num++;
            }
            System.out.println(Thread.currentThread().getName() + " -> " + num);
        }
    }
}
