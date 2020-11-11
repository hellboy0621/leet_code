package com.transformers.advanced.week03.class06.thread;

import java.util.concurrent.TimeUnit;

public class ThreadB extends Thread {
    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadB.run");

        Thread currentThread = Thread.currentThread();
        String currentThreadName = currentThread.getName();

        System.out.println(currentThreadName + " -> 线程组中活动线程的数量 -> " + Thread.activeCount());
        // System.out.println(currentThreadName + " -> 线程组中活动线程的数量 -> " + Thread.currentThread().getThreadGroup().activeCount());
        System.out.println(currentThreadName + " -> 标识符 -> " + currentThread.getId());
        System.out.println(currentThreadName + " -> 优先级 -> " + currentThread.getPriority());
        System.out.println(currentThreadName + " -> 状态 -> " + currentThread.getState());
        System.out.println(currentThreadName + " -> 所属线程组 -> " + currentThread.getThreadGroup());
        System.out.println(currentThreadName + " -> 是否处于活跃状态 -> " + currentThread.isAlive());
        System.out.println(currentThreadName + " -> 是否为守护线程 -> " + currentThread.isDaemon());


    }
}
