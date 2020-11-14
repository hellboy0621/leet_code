package com.transformers.advanced.week04.class08.future;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<Integer> future = exec.submit(() -> new Random().nextInt());
        exec.shutdown();
        try {
            System.out.println("result = " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread end.");
    }
}
