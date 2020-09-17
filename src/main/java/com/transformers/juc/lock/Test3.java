package com.transformers.juc.lock;

import java.util.concurrent.TimeUnit;

public class Test3 {
    public static void main(String[] args) {
        Phone3 phone = new Phone3();
        Phone3 phone2 = new Phone3();
        new Thread(phone::sendSms, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(phone2::call, "B").start();
    }
}

class Phone3 {
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
