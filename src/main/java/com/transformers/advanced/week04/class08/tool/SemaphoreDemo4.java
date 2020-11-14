package com.transformers.advanced.week04.class08.tool;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo4 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);
        new Thread(() -> {
            try {
//                semaphore.release();
                semaphore.acquire();
                System.out.println("SemaphoreDemo4.main");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
    }
}
