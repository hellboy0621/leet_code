package com.transformers.juc.functionalinterface;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };
        consumer = str -> System.out.println(str);
        consumer = System.out::println;
        consumer.accept("abc");
    }
}
