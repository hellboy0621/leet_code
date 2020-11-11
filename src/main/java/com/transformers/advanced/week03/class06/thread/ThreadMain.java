package com.transformers.advanced.week03.class06.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadMain {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.start();
        System.out.println("ThreadMain.main -> After threadA.start()");

        ThreadB threadB = new ThreadB();
        new Thread(threadB).start();
        System.out.println("ThreadMain.main -> After threadB.start()");


        ThreadC threadC = new ThreadC();
        FutureTask<String> futureTask = new FutureTask<>(threadC);
        new Thread(futureTask).start();
        System.out.println("ThreadMain.main -> After threadC.start()");

        try {
            System.out.println("ThreadC得到的返回结果 -> " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
