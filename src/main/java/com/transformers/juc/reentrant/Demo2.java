package com.transformers.juc.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock
public class Demo2 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(phone::sms, "A").start();
        new Thread(phone::sms, "B").start();
    }
}
class Phone2 {
    Lock lock = new ReentrantLock();
    public void sms() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Phone.sms");
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Phone.call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
/**
 * A Phone.sms
 * A Phone.call
 * B Phone.sms
 * B Phone.call
 */
