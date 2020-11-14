package com.transformers.advanced.week04.class08.tool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args) {
        int workerNum = 8;
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < workerNum; i++) {
            new Worker(i, semaphore).start();
        }
    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
//                semaphore.acquire(2);
                System.out.println("worker -> " + num + " acquire 1 machine " + Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(400);
                System.out.println("worker -> " + num + " release 1 machine " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
//                semaphore.release(2);
            }
        }
    }
}
