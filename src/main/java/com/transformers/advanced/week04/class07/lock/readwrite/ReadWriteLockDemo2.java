package com.transformers.advanced.week04.class07.lock.readwrite;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo2 {

    public static void main(String[] args) {
        ReadWriteLockDemo2 rwd = new ReadWriteLockDemo2();
        System.out.println(rwd.readWrite("abc"));
    }

    private HashMap<String, Object> cacheMap = new HashMap<>();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public Object readWrite(String key) {
        Object value = null;
        System.out.println("开启读锁，从缓存中获取");
        rwLock.readLock().lock();
        try {
            value = cacheMap.get(key);
            if (value == null) {
                System.out.println("关闭读锁");
                rwLock.readLock().unlock();
                System.out.println("开启写锁");
                rwLock.writeLock().lock();
                try {
                    if (value == null) {
                        value = "aaa";
                    }
                } finally {
                    System.out.println("关闭写锁");
                    rwLock.writeLock().unlock();
                }
                System.out.println("开启读锁");
                rwLock.readLock().lock();
            }
        } finally {
            System.out.println("关闭读锁，退出方法");
            rwLock.readLock().unlock();
        }
        return value;
    }

}
