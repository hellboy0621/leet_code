package com.transformers.advanced.week04.class07.lock.reentrantlock;

/**
 * 读锁不互斥、写锁互斥
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Count count = new Count();
        for (int i = 0; i < 2; i++) {
            new Thread(count::get).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(count::put).start();
        }
    }
    /**
     * Thread-0 -> get start
     * Thread-0 -> get end
     * Thread-1 -> get start
     * Thread-1 -> get end
     * Thread-2 -> put start
     * Thread-2 -> put end
     * Thread-3 -> put start
     * Thread-3 -> put end
     */
}
