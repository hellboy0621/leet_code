package com.transformers.advanced.week04.class08.collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 多个步骤的操作，不能保证原子性
 * list.size() 获取到的数，再继续用list时，可能已经变了
 * <p>
 * Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: 9808
 * at java.util.concurrent.CopyOnWriteArrayList.get(CopyOnWriteArrayList.java:388)
 * at java.util.concurrent.CopyOnWriteArrayList.get(CopyOnWriteArrayList.java:397)
 * at com.transformers.advanced.week04.class08.collection.CopyOnWriteArrayListDemo3.lambda$test$0(CopyOnWriteArrayListDemo3.java:32)
 * at java.lang.Thread.run(Thread.java:748)
 */
public class CopyOnWriteArrayListDemo3 {

    private static final List<String> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        for (int i = 0; i < 10000; i++) {
            list.add(String.valueOf(i));
        }
        new Thread(() -> {
            while (true) {
                /**
                 * 没有规则保证2个size()方法
                 */
                if (list.size() > 0) { // 下一个get操作执行时，size可能已经是0了
                    String result = list.get(list.size() - 1);
                } else {
                    break;
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                if (list.size() <= 0) {
                    break;
                }
                list.remove(0);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
