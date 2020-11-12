package com.transformers.advanced.week04.class07.atomic.count;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ICount c1 = new Count();
        ICount c2 = new SyncCount();
        ICount c3 = new AtomicCount();

        test(c1);
        test(c2);
        test(c3);

        TimeUnit.SECONDS.sleep(5);
        System.out.println(c1.get());
        System.out.println(c2.get());
        System.out.println(c3.get());
    }

    public static void test(ICount count) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    count.add();
                }
            }).start();
        }
    }
}
