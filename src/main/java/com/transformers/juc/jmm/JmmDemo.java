package com.transformers.juc.jmm;

import java.util.concurrent.TimeUnit;

// 保证可见性
public class JmmDemo {
    //    private static int num = 0;
    private static volatile int num = 0;
    public static void main(String[] args) throws InterruptedException {
        // num加volatile关键字之前，不能感知到主内存中的变化
        new Thread(() -> {
            while (num == 0) {}
        }).start();
        // 等待1s，让线程执行起来
        TimeUnit.SECONDS.sleep(1);
        num = 1;
        System.out.println("num = " + num);
    }
}
