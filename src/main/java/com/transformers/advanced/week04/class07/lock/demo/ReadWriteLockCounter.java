package com.transformers.advanced.week04.class07.lock.demo;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter {
    private ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private int num = 0;

    public int incrAndGet() {
        lock.writeLock().lock();
        try {
            return ++num;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getNum() {
        lock.readLock().lock();
        try {
            return num;
        } finally {
            lock.readLock().unlock();
        }
    }
}
