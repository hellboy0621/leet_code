package com.transformers.advanced.week03.class06.thread;

import java.util.concurrent.TimeUnit;

public class ThreadMain2 {
    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        for (int i = 0; i < 5; i++) {
            new Thread(threadB, String.valueOf(i)).start();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("*********************************");
        Thread thread = Thread.currentThread();
        System.out.println("当前线程组活动线程数量 -> " + Thread.activeCount());
        System.out.println("主线程名称 -> " + thread.getName());
        System.out.println("主线程标识符 -> " + thread.getId());
        System.out.println("主线程优先级 -> " + thread.getPriority());
        System.out.println("主线程状态 -> " + thread.getState());
        System.out.println("主线程所属线程组 -> " + thread.getThreadGroup());
        System.out.println("主线程是否为守护线程 -> " + thread.isDaemon());
    }
}
