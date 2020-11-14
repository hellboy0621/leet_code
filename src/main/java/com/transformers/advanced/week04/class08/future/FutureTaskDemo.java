package com.transformers.advanced.week04.class08.future;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            return new Random().nextInt();
        });
//        execTask(futureTask);
        execTask2(futureTask);
        try {
            int result = futureTask.get();
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread end.");
    }

    /**
     * 使用FutureTask封装Callable
     * 使用Thread执行
     *
     * @param futureTask
     */
    private static void execTask(FutureTask<Integer> futureTask) {
        new Thread(futureTask).start();
    }

    /**
     * 使用FutureTask封装Callable
     * 使用线程池执行
     *
     * @param futureTask
     */
    private static void execTask2(FutureTask<Integer> futureTask) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.submit(futureTask);
        exec.shutdown();
    }
}
