package com.transformers.advanced.week04.class08.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("一轮下来，所有子线程执行完毕。");
            System.out.println("这里是回调函数");
        });
        // 主线程里没有CyclicBarrier的相关阻塞代码
        for (int i = 0; i < 10; i++) {
            new Thread(new ReadTask(i, cyclicBarrier)).start();
        }
        System.out.println("Main Thread end.");
    }

    static class ReadTask implements Runnable {

        private int id;
        private CyclicBarrier barrier;

        public ReadTask(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            // 聚合点在每个线程里
            try {
                System.out.println(Thread.currentThread().getName() + " -> id[" + id + "]");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
