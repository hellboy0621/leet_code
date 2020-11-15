package com.transformers.advanced.week04.class08.courseware;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        IntStream.rangeClosed(1, 10_000)
                .forEach(list::add);
        BlockingQueue<Long> blockingQueue = new LinkedBlockingQueue<>(10000);
        List<Long> longList = list
                .stream()
                .parallel()
                .map(Integer::longValue)
                .sorted()
                .collect(Collectors.toList());
        // 并行
        longList.stream()
                .parallel()
                .forEach(i -> {
                    try {
                        blockingQueue.put(i);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
        System.out.println("blockingQueue " + blockingQueue.toString());
        System.out.println(list.size());
        System.out.println(blockingQueue.size());
    }
}
