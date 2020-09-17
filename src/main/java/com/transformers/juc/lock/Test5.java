package com.transformers.juc.lock;

import java.util.concurrent.TimeUnit;

public class Test5 {
    public static void main(String[] args) {
        Phone5 phone = new Phone5();
        Phone5 phone2 = new Phone5();
        new Thread(() -> phone.sendSms(), "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone2.call(), "B").start();
    }
}
class Phone5 {
    public static synchronized void call() {
        System.out.println("Phone.call");
    }
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Phone.sendSms");
    }
}
