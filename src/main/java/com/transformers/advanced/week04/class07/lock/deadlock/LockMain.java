package com.transformers.advanced.week04.class07.lock.deadlock;

public class LockMain {
    public static void main(String[] args) {
        Count3 count = new Count3();
        new Thread(count::lock1, "thread-1").start();
        new Thread(count::lock2, "thread-2").start();
    }
    /**
     * Java stack information for the threads listed above:
     * ===================================================
     * "thread-2":
     *         at com.transformers.advanced.week04.class07.lock.deadlock.Count3.commonLock(Count3.java:26)
     *         - waiting to lock <0x000000076b7e02d8> (a [B)
     *         - locked <0x000000076b7e02f0> (a [B)
     *         at com.transformers.advanced.week04.class07.lock.deadlock.Count3.lock2(Count3.java:15)
     *         at com.transformers.advanced.week04.class07.lock.deadlock.LockMain$$Lambda$2/769287236.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:748)
     * "thread-1":
     *         at com.transformers.advanced.week04.class07.lock.deadlock.Count3.commonLock(Count3.java:26)
     *         - waiting to lock <0x000000076b7e02f0> (a [B)
     *         - locked <0x000000076b7e02d8> (a [B)
     *         at com.transformers.advanced.week04.class07.lock.deadlock.Count3.lock1(Count3.java:11)
     *         at com.transformers.advanced.week04.class07.lock.deadlock.LockMain$$Lambda$1/2046562095.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:748)
     *
     * Found 1 deadlock.
     */
}
