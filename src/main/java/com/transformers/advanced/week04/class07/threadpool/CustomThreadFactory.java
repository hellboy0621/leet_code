package com.transformers.advanced.week04.class07.threadpool;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {
    private AtomicInteger serial = new AtomicInteger(0);

    @Override
    public Thread newThread(@NotNull Runnable r) {
        Thread thread = new Thread(r);
        // 根据需要，设置守护线程
        thread.setDaemon(true);
        thread.setName("CustomThread-" + serial.getAndIncrement());
        return thread;
    }
}
