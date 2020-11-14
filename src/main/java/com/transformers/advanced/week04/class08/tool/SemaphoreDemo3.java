package com.transformers.advanced.week04.class08.tool;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo3 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer(), "Producer-Thread-" + i).start();
            new Thread(new Consumer(), "Consumer-Thread-" + i).start();
        }
    }

    static WareHouse buffer = new WareHouse();

    static class Producer implements Runnable {

        static int num = 0;

        @Override
        public void run() {
            final int n = ++num;
            while (true) {
                try {
                    buffer.put(n);
                    System.out.println(Thread.currentThread().getName() + "\t-> put \t" + n);
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                try {
                    int n = buffer.take();
                    System.out.println(Thread.currentThread().getName() + "\t-> take\t" + n);
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class WareHouse {

        private int[] items = new int[20];

        private Semaphore notFull = new Semaphore(10);
        private Semaphore notEmpty = new Semaphore(0);
        private Semaphore mutex = new Semaphore(1);

        int putIndex, takeIndex, count;

        public void put(int n) throws InterruptedException {
            notFull.acquire();
            mutex.acquire();
            try {
                items[putIndex] = n;
                if (++putIndex == items.length) {
                    putIndex = 0;
                }
                ++count;
                System.out.println("WareHouse.put items -> " + Arrays.toString(items));
            } finally {
                mutex.release();
                notEmpty.release();
            }
        }

        public int take() throws InterruptedException {
            notEmpty.acquire();
            mutex.acquire();
            try {
                int result = items[takeIndex];
                if (++takeIndex == items.length) {
                    takeIndex = 0;
                }
                --count;
                System.out.println("WareHouse.take items -> " + Arrays.toString(items));
                return result;
            } finally {
                mutex.release();
                notFull.release();
            }
        }
    }
}
