package com.transformers.advanced.week03.class06.thread;

import java.util.concurrent.TimeUnit;

public class ThreadA extends Thread{
    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadA.run");
    }
}
