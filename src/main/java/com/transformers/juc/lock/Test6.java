package com.transformers.juc.lock;

import java.util.concurrent.TimeUnit;

public class Test6 {
    public static void main(String[] args) {
        Phone6 phone = new Phone6();
        new Thread(() -> phone.sendSms(), "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone.call(), "B").start();
    }
}
class Phone6 {
    public synchronized void call() {
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
