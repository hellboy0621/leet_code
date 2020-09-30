package com.transformers.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 *
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;

    private Long end;

    private Long criticalValue = 1000L;

    public ForkJoinDemo(Long start, Long end, Long criticalValue) {
        this.start = start;
        this.end = end;
        this.criticalValue = criticalValue;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if ((end - start) < criticalValue) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long mid = (start + end) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, mid, criticalValue);
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(mid + 1, end, criticalValue);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
