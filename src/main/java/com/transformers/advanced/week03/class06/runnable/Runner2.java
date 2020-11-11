package com.transformers.advanced.week03.class06.runnable;

public class Runner2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("进入Runner2运行状态 -> " + i);
        }
        boolean interrupted1 = Thread.currentThread().isInterrupted();
        // 重置状态
        boolean interrupted2 = Thread.interrupted();
        boolean interrupted3 = Thread.currentThread().isInterrupted();
        System.out.println("Runner2.run interrupted1 -> " + interrupted1);
        System.out.println("Runner2.run interrupted2 -> " + interrupted2);
        System.out.println("Runner2.run interrupted3 -> " + interrupted3);
    }
}
