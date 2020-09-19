package com.transformers.juc.callable;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class CallableDemo {
    public static void main(String[] args)
            throws ExecutionException, InterruptedException {
        // 可以抛异常
        MyThread myThread = new MyThread();
        FutureTask<Integer> futureTask = new FutureTask<>(myThread);
        new Thread(futureTask).start();
        // 有缓存
        new Thread(futureTask).start();
        // 阻塞
        Integer result = futureTask.get();
        log.info("result -> " + result);
    }
}
@Slf4j
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        log.info("MyThread.call");
        return 1 << 10;
    }
}
