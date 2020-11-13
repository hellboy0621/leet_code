package com.transformers.advanced.week04.class07.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCondition {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        new Thread(task).start();
        Thread.sleep(1000);
        Task.lock.lock();
        Task.condition.signal();
        // 释放锁的代码如果注释掉，虽然唤醒了线程
        // 但是由于无法获取main线程持有但未释放的锁lock，而无法继续执行
        Task.lock.unlock();
    }

    public static class Task implements Runnable {
        public static ReentrantLock lock = new ReentrantLock();
        public static Condition condition = lock.newCondition();

        @Override
        public void run() {
            lock.lock();
            try {
                condition.await();
                System.out.println("Thread is going on.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
