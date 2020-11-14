package com.transformers.advanced.week04.class08.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        System.out.println("1.变换结果");
        String result1 = CompletableFuture.supplyAsync(() -> "hello ")
                .thenApplyAsync(t -> t + "future world")
                .join();
        System.out.println(result1);

        CompletableFuture.supplyAsync(() -> "hello ")
                .thenAccept(t -> {
                    System.out.println("2.消费");
                    System.out.println("consumer -> " + t);
                });

        System.out.println("3.组合");
        String result3 = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Hello";
                })
                .thenCombine(CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "World";
                }), (t, u) -> t + " " + u)
                .join();
        System.out.println(result3);

        System.out.println("4.竞争");
        String result4 = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Hi, boys";
                })
                .applyToEither(CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Hi, girls";
                }), t -> t)
                .join();
        System.out.println(result4);

        System.out.println("5.异常处理");
        String result5 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("exception test.");
            }
            return "nomoral ";
        }).exceptionally(e -> {
            e.printStackTrace();
            return "exception";
        }).join();
        System.out.println(result5);
    }
}
