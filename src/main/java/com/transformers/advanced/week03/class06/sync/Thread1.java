package com.transformers.advanced.week03.class06.sync;

public class Thread1 implements Runnable {
    @Override
    public void run() {
        synchronized(this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop -> " + i);
            }
        }
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread ta = new Thread(thread1);
        Thread tb = new Thread(thread1);
        ta.start();
        tb.start();
    }
}
