package com.transformers.juc.threadpool;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 本机CPU核数
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.DiscardPolicy()
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        test(executorService);
    }

    public static void test(ExecutorService executorService) {
        try {
            for (int i = 1; i <= 9; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
