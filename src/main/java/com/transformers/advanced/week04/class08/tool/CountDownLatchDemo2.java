package com.transformers.advanced.week04.class08.tool;

import java.util.Random;
import java.util.concurrent.*;

public class CountDownLatchDemo2 {
    private static int threadCount = 20;

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(threadCount);
        ExecutorService exec = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                5, TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0; i < threadCount; i++) {
            final int num = i;
            exec.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                    System.out.println(Thread.currentThread().getName() + " -> " + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 放在finally块里，防止业务代码异常后阻塞
                    cdl.countDown();
                }
            });
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有任务执行完成！");
        System.out.println("Main Thread end.");
        exec.shutdown();
    }
}
