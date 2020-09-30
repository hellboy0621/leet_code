package com.transformers.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();
//        test2();
        test3();
        // sum = 500000000500000000, time = 305
        // sum = 500000000500000000, time = 347
        // sum = 500000000500000000, time = 216
    }

    public static void test1() {
        long sum = 0;
        long start = System.currentTimeMillis();
        for (long i = 0; i <= 10_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + ", time = " + (end - start));
    }

    public static void test2() throws ExecutionException, InterruptedException {
        long sum = 0;
        long start = System.currentTimeMillis();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L, 1000L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + ", time = " + (end - start));
    }

    public static void test3() {
        // Stream并行流
        long sum = 0;
        long start = System.currentTimeMillis();
        sum = LongStream.rangeClosed(0, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + ", time = " + (end - start));
    }
}
