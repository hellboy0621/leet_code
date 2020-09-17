package com.transformers.juc.lock;

import java.util.concurrent.TimeUnit;

public class Test7 {
    public static void main(String[] args) {
        Phone7 phone = new Phone7();
        Phone7 phone2 = new Phone7();
        new Thread(() -> phone.sendSms(), "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone2.call(), "B").start();
    }
}
class Phone7 {
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
