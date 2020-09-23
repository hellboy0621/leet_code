package com.transformers.juc.tool;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 线程数量 限流
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    log.info("{} 抢到车位", Thread.currentThread().getName());
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                    log.info("{} 离开车位", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i + 1)).start();
        }

    }
}
