package com.transformers.advanced.week03.class06.join;

public class JoinDemo2 {
    public static void main(String[] args) {
        Object obj = new Object();

        MyThread2 myThread2 = new MyThread2("myThread");
        myThread2.setObj(obj);
        myThread2.start();

        synchronized (myThread2) {
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    try {
                        /**
                         * 当前线程里调用其它线程myThread的join方法
                         * 当前线程main进入WAITING/TIMED_WAITING状态
                         * 当前线程main不会释放已经持有的对象锁
                         * 但是会释放myThread2的对象锁
                         *
                         * main线程wait，等待myThread2执行完毕后，继续执行
                         */
                        myThread2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -> " +  i);
            }
        }
    }
}

class MyThread2 extends Thread {
    private String name;
    private Object obj;

    public MyThread2(String name) {
        this.name = name;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(name + " -> " + i);
            }
        }
    }
}
