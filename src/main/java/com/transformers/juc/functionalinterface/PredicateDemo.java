package com.transformers.juc.functionalinterface;

import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return str == null || str.isEmpty();
            }
        };
        predicate = str -> str == null || str.isEmpty();
        System.out.println(predicate.test("abc"));
    }
}
