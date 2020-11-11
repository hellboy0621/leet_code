package com.transformers.advanced.week03.class06.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ThreadC implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("ThreadC.call");
        return "ThreadC.call.result";
    }
}
