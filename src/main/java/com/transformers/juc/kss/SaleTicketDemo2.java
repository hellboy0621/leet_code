package com.transformers.juc.kss;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo2 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {for (int i = 0; i < 50; i++) ticket.sale(); }, "A").start();
        new Thread(() -> {for (int i = 0; i < 50; i++) ticket.sale(); }, "B").start();
        new Thread(() -> {for (int i = 0; i < 50; i++) ticket.sale(); }, "C").start();
    }
}

// OOP 解耦，不要实现Runnable
@Slf4j
class Ticket2 {

    private int num = 50;

    Lock lock = new ReentrantLock();

    // 买票的方式
    public void sale() {
        lock.lock();
        try {
            if (num > 0) {
                log.info("{}卖出了第{}张票，还剩{}", Thread.currentThread().getName(), num--, num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
