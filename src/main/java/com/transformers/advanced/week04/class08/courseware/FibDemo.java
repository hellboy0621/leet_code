package com.transformers.advanced.week04.class08.courseware;

import org.springframework.util.StopWatch;

/**
 * 斐波那契数列
 */
public class FibDemo {

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start("fib1");
        System.out.println(fib1(36));
        sw.stop();
        sw.start("fib2");
        System.out.println(fib2(36));
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

    public static long fib1(int level) {
        long[] items = new long[level + 1];
        items[0] = 1;
        items[1] = 1;
        for (int i = 2; i <= level; i++) {
            items[i] = items[i - 1] + items[i - 2];
        }
        return items[level];
    }

    public static long fib2(int level) {
        if (level < 2) {
            return 1;
        }
        return fib2(level - 1) + fib2(level - 2);
    }
    /**
     * 24157817
     * 24157817
     * StopWatch '': running time = 90073800 ns
     * ---------------------------------------------
     * ns         %     Task name
     * ---------------------------------------------
     * 000272800  000%  fib1
     * 089801000  100%  fib2
     *
     * Process finished with exit code 0
     */
}
