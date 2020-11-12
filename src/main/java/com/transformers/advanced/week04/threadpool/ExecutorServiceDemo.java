package com.transformers.advanced.week04.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("ExecutorServiceDemo.main.submit");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("ExecutorServiceDemo.main");
        try {
            executorService.shutdownNow();
            executorService.awaitTermination(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
