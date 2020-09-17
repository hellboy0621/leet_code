package com.transformers.juc.pc;

import lombok.extern.slf4j.Slf4j;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

// 等待 业务 通知
@Slf4j
class Data {

    private int num = 0;

    // +1
    public synchronized void increment() throws InterruptedException {
//        if (num != 0) {
//            this.wait();
//        }
        while (num != 0) {
            this.wait();
        }
        num++;
        log.info("{}=>{}", Thread.currentThread().getName(), num);
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {
//        if (num == 0) {
//            this.wait();
//        }
        while (num == 0) {
            this.wait();
        }
        num--;
        log.info("{}=>{}", Thread.currentThread().getName(), num);
        this.notifyAll();
    }
}
