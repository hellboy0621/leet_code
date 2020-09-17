package com.transformers.juc.lock;

import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(phone::sendSms, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(phone::hello, "B").start();
    }
}
class Phone2 {
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
    // 普通方法，不受锁的影响
    public void hello() {
        System.out.println("Phone2.hello");
    }
}
