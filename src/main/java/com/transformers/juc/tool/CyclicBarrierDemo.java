package com.transformers.juc.tool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Slf4j
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 集齐7颗龙珠召唤神龙
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            log.info("召唤神龙成功！");
        });
        for (int i = 0; i < 7; i++) {
            int tmp = i;
            new Thread(() -> {
                log.info("收集{}个龙珠", tmp + 1);
                try {
                    // 等待
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
