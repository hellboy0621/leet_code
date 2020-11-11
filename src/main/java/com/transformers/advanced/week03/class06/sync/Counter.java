package com.transformers.advanced.week03.class06.sync;

public class Counter {
    // 增加volatile关键字也不能保证原子性
    private volatile int sum = 0;

    // 增加synchronized关键字解决问题
    public synchronized void incr() {
        sum += 1;
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) {
        int loop = 100000;
        // test single thread
        Counter counter = new Counter();
        for (int i = 0; i < loop; i++) {
            counter.incr();
        }
        System.out.println("single thread -> " + counter.getSum());

        // test multiple thread
        final Counter counter2 = new Counter();
        int threads = 10;
        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                for (int j = 0; j < loop / threads; j++) {
                    counter2.incr();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("multiple threads -> " + counter2.getSum());
    }
}
