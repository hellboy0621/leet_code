package com.transformers.juc.reentrant;

// synchronized
public class Demo1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(phone::sms, "A").start();
        new Thread(phone::sms, "B").start();
    }
}
class Phone{
    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + " Phone.sms");
        call();
    }
    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + " Phone.call");
    }
}
/**
 * A Phone.sms
 * A Phone.call
 * B Phone.sms
 * B Phone.call
 */
