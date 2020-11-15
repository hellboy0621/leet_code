package com.transformers.advanced.week04.class08.collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteArrayListDemo2 {
    private static final int THREAD_POOL_MAX_NUM = 10;
    //    private List<String> list = new ArrayList<>();
    private List<String> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        new CopyOnWriteArrayListDemo2().start();
    }

    private void start() {
        init(list);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_POOL_MAX_NUM; i++) {
            exec.execute(new ListReader(list));
            exec.execute(new ListWriter(list, i));
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("********************************************");
        System.out.println("********************************************");
        for (int i = 0; i < THREAD_POOL_MAX_NUM; i++) {
            exec.execute(new ListReader(list));
        }
        exec.shutdown();
    }

    private void init(List<String> list) {
        for (int i = 0; i < THREAD_POOL_MAX_NUM; i++) {
            list.add("############init" + (i + 1) + "############");
        }
    }

    class ListReader implements Runnable {
        private List<String> list;

        public ListReader(List<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (String str : list) {
                System.out.println(Thread.currentThread().getName() + " -> " + str);
            }
        }
    }

    class ListWriter implements Runnable {
        private List<String> list;
        private int index;

        public ListWriter(List<String> list, int index) {
            this.list = list;
            this.index = index;
        }

        @Override
        public void run() {
            if (list != null) {
                list.add("***********add" + index + "***********");
            }
        }
    }
}
