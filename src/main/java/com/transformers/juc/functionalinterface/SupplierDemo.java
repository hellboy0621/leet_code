package com.transformers.juc.functionalinterface;

import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "abc";
            }
        };
        supplier = () -> "abc";
        System.out.println(supplier.get());
    }
}
