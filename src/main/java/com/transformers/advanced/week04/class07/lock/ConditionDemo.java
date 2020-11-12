package com.transformers.advanced.week04.class07.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock/Condition实现生产消费者模型
 */
public class ConditionDemo {

    public static void main(String[] args) {
        ConditionDemo cd = new ConditionDemo();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    cd.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "productor").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    cd.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
    }

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private Object[] items = new Object[10];
    private int putptr, takeptr, count;

    public void put(Object obj) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putptr++] = obj;
            System.out.println(Thread.currentThread().getName() + " -> ConditionDemo.put -> " + obj);
            if (putptr == items.length) {
                putptr = 0;
            }
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object obj = items[takeptr++];
            System.out.println(Thread.currentThread().getName() + " -> ConditionDemo.take -> " + obj);
            if (takeptr == items.length) {
                takeptr = 0;
            }
            count--;
            notFull.signal();
            return obj;
        } finally {
            lock.unlock();
        }
    }
}
