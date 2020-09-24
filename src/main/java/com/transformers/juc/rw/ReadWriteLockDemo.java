package com.transformers.juc.rw;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            int tmp = i + 1;
            new Thread(() -> {
                myCache.put(tmp + "", tmp + "");
            }, String.valueOf(i + 1)).start();
        }

        for (int i = 5; i < 10; i++) {
            int tmp = i + 1;
            new Thread(() -> {
                myCache.get(tmp + "");
            }, String.valueOf(i + 1)).start();
        }
    }
}

/**
 * 自定义缓存
 */
@Slf4j
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 存， 写
    public void put(String key, Object val) {
        readWriteLock.writeLock().lock();
        try {
            log.info("{} 写入 {}", Thread.currentThread().getName(), key);
            map.put(key, val);
            log.info("{} 写入 OK", Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    // 取， 读
    public Object get(String key) {
        readWriteLock.readLock().lock();
        Object obj = null;
        try {
            log.info("{} 读取 {}", Thread.currentThread().getName(), key);
            obj = map.get(key);
            log.info("{} 读取 OK", Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return obj;
    }
}
