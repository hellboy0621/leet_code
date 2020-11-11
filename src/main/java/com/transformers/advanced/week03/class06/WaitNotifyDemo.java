package com.transformers.advanced.week03.class06;

import java.util.concurrent.TimeUnit;

public class WaitNotifyDemo {
    public static void main(String[] args) {
        MethodClass methodClass = new MethodClass();
        Thread thread1 = new Thread(() -> {
            try {
                methodClass.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        Thread thread2 = new Thread(() -> {
            try {
                methodClass.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        Thread thread3 = new Thread(() -> {
            try {
                methodClass.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class MethodClass {
    private final int MAX_COUNT = 5;
    int productCount = 0;
    public synchronized void product() throws InterruptedException {
        while (true) {
            TimeUnit.MILLISECONDS.sleep(10);
            if (productCount >= MAX_COUNT) {
                System.out.println(Thread.currentThread().getName() + " -> 货仓已满，无法生产");
                wait();
            } else {
                System.out.println(Thread.currentThread().getName() + " -> " + productCount);
                productCount++;
            }
            notifyAll();
        }
    }

    public synchronized void consume() throws InterruptedException {
        while (true) {
            if (productCount <= 0) {
                System.out.println(Thread.currentThread().getName() + " -> 货仓已空，无法消费");
                wait();
            } else {
                System.out.println(Thread.currentThread().getName() + " -> " + productCount);
                productCount--;
            }
            notifyAll();
        }
    }
}