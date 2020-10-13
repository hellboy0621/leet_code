package com.transformers.juc.deadlock;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new MyThread(lockA, lockB), "T1").start();
        new Thread(new MyThread(lockB, lockA), "T2").start();
    }
}
@Data
@AllArgsConstructor
class MyThread implements Runnable {
    private String lockA;
    private String lockB;
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " lock:" + lockA + " get " + lockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + " lock:" + lockB + " get " + lockA);
            }
        }
    }
}
/**
 * Found one Java-level deadlock:
 * =============================
 * "T2":
 *   waiting to lock monitor 0x000001dbfb603728 (object 0x000000076b795510, a java.lang.String),
 *   which is held by "T1"
 * "T1":
 *   waiting to lock monitor 0x000001dbfb605da8 (object 0x000000076b795548, a java.lang.String),
 *   which is held by "T2"
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "T2":
 *         at com.transformers.juc.deadlock.MyThread.run(DeadLockDemo.java:34)
 *         - waiting to lock <0x000000076b795510> (a java.lang.String)
 *         - locked <0x000000076b795548> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:748)
 * "T1":
 *         at com.transformers.juc.deadlock.MyThread.run(DeadLockDemo.java:34)
 *         - waiting to lock <0x000000076b795548> (a java.lang.String)
 *         - locked <0x000000076b795510> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:748)
 *
 * Found 1 deadlock.
 */
