package com.transformers.advanced.week04.class07.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int num = i;
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + " start -> " + num);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end   -> " + num);
            };
//            scheduledExecutorService.schedule(task, 2, TimeUnit.SECONDS);
            scheduledExecutorService.scheduleAtFixedRate(task, 2, 3, TimeUnit.SECONDS);
        }
        /**
         * 使用schedule后调用shutdown，会执行完任务后停止线程池。
         * 如果使用scheduleAtFixedRate后调用shutdown，会立即停止线程池。
         */
//        scheduledExecutorService.shutdown();
        System.out.println("Main thread end.");
    }
}
