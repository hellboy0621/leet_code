package com.transformers.juc.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
public class SetDemo {

    public static void main(String[] args) {
        // java.util.ConcurrentModificationException
        // Set<String> set = new HashSet<>();
        // Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 4));
                log.info(set.toString());
            }, String.valueOf(i)).start();
        }
    }

}
