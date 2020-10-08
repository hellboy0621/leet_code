package com.transformers.juc.jmm;

// volatile 不保证原子性
// 但synchronized 可以保证原子性
public class JmmDemo2 {
    private static volatile int num = 0;
    //    private static synchronized void add() {
    private static void add() {
        num++;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " num = " + num);
    }
}
