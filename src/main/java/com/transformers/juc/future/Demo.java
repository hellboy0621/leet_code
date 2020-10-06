package com.transformers.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();
        test2();
    }

    public static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " runAsync -> Void");
        });
        System.out.println("Demo.test1 - before get");
        // 获取阻塞执行结果
        completableFuture.get();
        System.out.println("Demo.test1 - after get");
    }

    public static void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " supplyAsync -> Integer");
            int i = 10 / 0;
            return 1024;
        });
        Integer i = completableFuture
                // 成功回调
                .whenComplete((t, u) -> {
                    // 正常返回结果
                    System.out.println("t => " + t);
                    // 异常时错误信息 u => java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
                    System.out.println("u => " + u);
                })
                // 失败回调
                .exceptionally((e) -> {
                    System.out.println(e.getMessage());
                    return -1;
                }).get();
        System.out.println(i);
    }

}
