package com.transformers.advanced.week04.class07.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static final Object LOCK = new Object();
    static Thread t1 = new ChangeObjectThread("t1");
    static Thread t2 = new ChangeObjectThread("t2");

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }

    public static class ChangeObjectThread extends Thread {
        ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (LOCK) {
                System.out.println(System.currentTimeMillis() + " in " + getName());
                // 暂停当前线程，响应中断
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(System.currentTimeMillis() + " " + getName() + " 被中断了");
                }
                System.out.println(System.currentTimeMillis() + " " + getName() + " 执行完成");
            }
        }
    }
    /**
     * 1605170215390 in t1
     * 1605170218391 t1 被中断了
     * 1605170218391 t1 执行完成
     * 1605170218391 in t2
     * 1605170218391 t2 执行完成
     */
}
