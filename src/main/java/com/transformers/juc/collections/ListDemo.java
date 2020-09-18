package com.transformers.juc.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class ListDemo {
    public static void main(String[] args) {
        // java.util.ConcurrentModificationException 并发修改异常
        // List<String> list = new ArrayList<>();
        // List<String> list = new Vector<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 4));
                log.info(list.toString());
            }, String.valueOf(i)).start();
        }
    }
}
