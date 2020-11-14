package com.transformers.advanced.week04.class08.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo2 {

    public static void main(String[] args) {
        int num = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num, () -> {
            // 等任务执行完成后，随便找一个之前的线程来执行这一段代码
            System.out.println(Thread.currentThread().getName() + " -> 所有任务执行完毕，集合完成");
        });
        for (int i = 0; i < num; i++) {
            new Thread(new WriteTask(cyclicBarrier), "WriteTask-" + (i + 1)).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 可以重复利用
        for (int i = 10; i < 10 + num; i++) {
            new Thread(new WriteTask(cyclicBarrier), "WriteTask-" + (i + 1)).start();
        }
        System.out.println("Main Thread end.");
    }

    static class WriteTask implements Runnable {
        private CyclicBarrier barrier;

        public WriteTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " -> 开始执行写任务");
            try {
                // 使用sleep方法模拟写入方法
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(Thread.currentThread().getName() + " -> 写任务执行完毕");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " -> 所有任务执行完毕");
        }
    }
}
