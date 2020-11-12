package com.transformers.advanced.week04.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NewCachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            final int num = i;
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\tstart ->\t" + num);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\tend   ->\t" + num);
            });
        }
        executorService.shutdown();
        System.out.println("Main thread end.");
    }
}
