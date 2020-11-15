package com.transformers.advanced.week04.class08.collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 只有CopyOnWriteArrayList才不会报错
 * 虽然Vector单个方法加synchronized同步关键字了，但是读写操作不是原子操作，所以也会报错
 */
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        List<Integer> list = new Vector<>();
//        List<Integer> list = new LinkedList<>();
        List<Integer> list = new CopyOnWriteArrayList<>();
        initList(list);
        new Thread(new ReadTask(list)).start();
        new Thread(new RemoveTask(list)).start();
    }

    private static void initList(List<Integer> list) {
        for (int i = 0; i < 10_000; i++) {
            list.add(i);
        }
    }

    static class ReadTask implements Runnable {
        private List<Integer> list;

        public ReadTask(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (Integer i : list) {

            }
        }
    }

    static class RemoveTask implements Runnable {
        private List<Integer> list;

        public RemoveTask(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < list.size(); i++) {
                list.remove(i);
            }
        }
    }
}
