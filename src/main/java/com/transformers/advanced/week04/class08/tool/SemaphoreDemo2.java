package com.transformers.advanced.week04.class08.tool;

import java.util.concurrent.*;

public class SemaphoreDemo2 {

    private static int threadCount = 20;

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                5, TimeUnit.SECONDS, new SynchronousQueue<>());
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < threadCount; i++) {
            final int num = i;
            exec.submit(() -> {
                test(num, semaphore);
            });
        }
        // 模拟 Executors.newCachedThreadPool()，如果不使用shutdown方法，等待所有线程被回收了，线程池自行关闭
//        exec.shutdown();
    }

    private static void test(int num, Semaphore semaphore) {
        try {
            // 如果获取全部许可，退化成串行执行
            semaphore.acquire(5);
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println(Thread.currentThread().getName() + " -> " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(5);
        }
    }

}
