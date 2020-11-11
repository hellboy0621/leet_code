package com.transformers.advanced.week03.class06.runnable;

public class ThreadGroupDemo {
    public static void main(String[] args) {
        System.out.println("************************");
        System.out.println(Thread.activeCount());
        System.out.println("************************");
        Thread.currentThread().getThreadGroup().list();
        System.out.println("************************");
        System.out.println(Thread.currentThread().getThreadGroup().getParent().activeGroupCount());
        System.out.println("************************");
        Thread.currentThread().getThreadGroup().getParent().list();
        System.out.println("************************");
    }
}
