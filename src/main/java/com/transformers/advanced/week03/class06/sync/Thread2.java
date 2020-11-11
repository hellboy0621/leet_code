package com.transformers.advanced.week03.class06.sync;

import java.util.concurrent.TimeUnit;

public class Thread2 {

    public static void main(String[] args) {
        final Thread2 thread2 = new Thread2();
        new Thread(thread2::m4t1, "t1").start();
        new Thread(thread2::m4t2, "t2").start();
    }

    /**
     * t1 -> 4
     * t1 -> 3
     * t1 -> 2
     * t1 -> 1
     * t1 -> 0
     * t2 -> 4
     * t2 -> 3
     * t2 -> 2
     * t2 -> 1
     * t2 -> 0
     */
    public void m4t1() {
        synchronized (this) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void m4t2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " -> " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
