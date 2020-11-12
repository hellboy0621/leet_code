package com.transformers.advanced.week04.class07.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NewSingleThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 3; i++) {
            final int num = i;
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " -> start -> " + num);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -> end   -> " + num);
            });
        }
        executorService.shutdown();
        System.out.println("Main thread end.");
    }

}
