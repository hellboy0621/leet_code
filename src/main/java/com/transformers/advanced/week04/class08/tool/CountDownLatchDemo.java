package com.transformers.advanced.week04.class08.tool;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        int num = 5;
        CountDownLatch cdl = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            new Thread(new ReadTask(i, cdl), "CountDownLatch-" + i).start();
        }
        try {
            // 主线程阻塞，等待计数器减为0
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("各个子线程执行完毕。");
        System.out.println("Main Thread end.");
    }

    static class ReadTask implements Runnable {
        private int id;
        private CountDownLatch countDownLatch;

        public ReadTask(int id, CountDownLatch countDownLatch) {
            this.id = id;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " -> id[" + id + "]");
            countDownLatch.countDown();
        }
    }
}
