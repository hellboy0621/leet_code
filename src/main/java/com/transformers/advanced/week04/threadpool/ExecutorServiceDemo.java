package com.transformers.advanced.week04.threadpool;

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
//            executorService.shutdown();
            executorService.shutdownNow();
            System.out.println(executorService.awaitTermination(1, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * awaitTermination需要结合shutdown或shutdownNow方法使用
     *
     * executorService.shutdown();
     *
     * ExecutorServiceDemo.main
     * false
     * ExecutorServiceDemo.main.submit
     */
    /**
     * executorService.shutdownNow();
     *
     * ExecutorServiceDemo.main
     * true
     * java.lang.InterruptedException: sleep interrupted
     * 	at java.lang.Thread.sleep(Native Method)
     * 	at java.lang.Thread.sleep(Thread.java:340)
     * 	at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     * 	at com.transformers.advanced.week04.threadpool.ExecutorServiceDemo.lambda$main$0(ExecutorServiceDemo.java:12)
     * 	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
     * 	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
     * 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
     * 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
     * 	at java.lang.Thread.run(Thread.java:748)
     */
}
