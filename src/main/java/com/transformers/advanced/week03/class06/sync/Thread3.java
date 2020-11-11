package com.transformers.advanced.week03.class06.sync;

import java.util.concurrent.TimeUnit;

public class Thread3 {

    public static void main(String[] args) {
        final Thread3 thread3 = new Thread3();
        final Inner inner = thread3.new Inner();
        new Thread(() -> {
            thread3.m4t1(inner);
        }, "t1").start();
        new Thread(() -> {
            thread3.m4t2(inner);
        }, "t2").start();
    }

    /**
     * t1 -> Inner.m4t1 -> 4
     * t2 -> Inner.m4t2 -> 4
     * t2 -> Inner.m4t2 -> 3
     * t1 -> Inner.m4t1 -> 3
     * t1 -> Inner.m4t1 -> 2
     * t2 -> Inner.m4t2 -> 2
     * t1 -> Inner.m4t1 -> 1
     * t2 -> Inner.m4t2 -> 1
     * t1 -> Inner.m4t1 -> 0
     * t2 -> Inner.m4t2 -> 0
     */

    private void m4t1(Inner inner) {
        synchronized (inner) {
            inner.m4t1();
        }
    }

    private void m4t2(Inner inner) {
        inner.m4t2();
    }

    class Inner {
        private void m4t1() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " -> Inner.m4t1 -> " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void m4t2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " -> Inner.m4t2 -> " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
