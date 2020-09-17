package com.transformers.juc.lock;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(phone::sendSms, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(phone::call, "B").start();
    }
}
class Phone {
    public synchronized void call() {
        System.out.println("Phone.call");
    }
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Phone.sendSms");
    }
}
