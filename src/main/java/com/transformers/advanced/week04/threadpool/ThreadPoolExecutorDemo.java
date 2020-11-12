package com.transformers.advanced.week04.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

    public static ThreadPoolExecutor initThreadPoolExecutor() {
        int coreSize = Runtime.getRuntime().availableProcessors();
        int maxSize = Runtime.getRuntime().availableProcessors() * 2;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(500);
        CustomThreadFactory threadFactory = new CustomThreadFactory();
        return new ThreadPoolExecutor(coreSize,
                maxSize,
                1,
                TimeUnit.MINUTES,
                workQueue,
                threadFactory);
    }
}
