package com.transformers.advanced.week04.class08.courseware;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierTask implements Runnable {
    private CyclicBarrier barrier;

    public CyclicBarrierTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int millis = new Random().nextInt(1000);
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
            // 线程阻塞
            this.barrier.await();
            System.out.println(Thread.currentThread().getName() + " -> begin to do sth.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    static class Main {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            // 如果数量过大会发生什么情况?
            int num = 2;
            CyclicBarrier barrier = new CyclicBarrier(num);
            List<CompletableFuture> list = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                CompletableFuture<Void> future =
                        CompletableFuture.runAsync(new CyclicBarrierTask(barrier));
                list.add(future);
            }
            for (CompletableFuture future : list) {
                future.get();
            }
            barrier.reset();
        }
    }
}
