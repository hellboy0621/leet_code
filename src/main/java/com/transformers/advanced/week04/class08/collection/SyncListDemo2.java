package com.transformers.advanced.week04.class08.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SyncListDemo2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        List<Integer> synchronizedList = Collections.synchronizedList(list);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                System.out.println(synchronizedList.size());
            }).start();
        }
        for (int i = 0; i < 1000; i++) {

            new Thread(() -> {
                synchronized (synchronizedList) {
                    Iterator<Integer> iterator = synchronizedList.iterator();
                    while (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                }
            }).start();
        }
        for (int i = 0; i < 1000; i++) {
            final int num = i;
            new Thread(() -> {
                System.out.println(synchronizedList.add(num));
            }).start();
        }
    }
}
