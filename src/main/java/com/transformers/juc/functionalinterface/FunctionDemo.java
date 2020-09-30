package com.transformers.juc.functionalinterface;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };
        function = (str) -> {
            return str;
        };
        function = (str) -> str;
        System.out.println(function.apply("abc"));
    }
}
