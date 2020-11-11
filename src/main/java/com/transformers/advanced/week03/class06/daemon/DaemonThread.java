package com.transformers.advanced.week03.class06.daemon;

import java.util.concurrent.TimeUnit;

public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("aaaaaaaaaa");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread thread = Thread.currentThread();
                System.out.println("当前线程：" + thread.getName());
            }
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        thread.setDaemon(true);
        thread.start();
//        thread.join();
    }

}
