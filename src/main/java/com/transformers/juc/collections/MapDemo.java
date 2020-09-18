package com.transformers.juc.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MapDemo {
    public static void main(String[] args) {
        // 工作中不用hashMap
        // java.util.ConcurrentModificationException
        // Map<String, String> map = new HashMap<>(16, 0.75F);
        // Map<String, String> map = new HashMap<>();
        // ConcurrentHashMap原理 - 官方文档
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                log.info(map.toString());
            }, String.valueOf(i)).start();
        }
    }
}
