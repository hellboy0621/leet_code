package com.transformers.advanced.week04.class08.courseware;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTask implements Runnable {
    private CountDownLatch latch;

    public CountDownLatchTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        int millis = new Random().nextInt(1000);
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
            this.latch.countDown();
            System.out.println(Thread.currentThread().getName() + " -> My job is done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Main {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            int num = 100;
            CountDownLatch cdl = new CountDownLatch(num);
            List<CompletableFuture> list = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                CompletableFuture<Void> future =
                        CompletableFuture.runAsync(new CountDownLatchTask(cdl));
                list.add(future);
            }
            cdl.await();
            System.out.println("******************** All job is done. ******************** ");
            for (CompletableFuture completableFuture : list) {
                completableFuture.get();
            }
        }
    }
}
