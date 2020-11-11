package com.transformers.advanced.week03.class06.join;

public class JoinDemo {
    public static void main(String[] args) {
        Object obj = new Object();

        MyThread myThread = new MyThread("myThread");
        myThread.setObj(obj);
        myThread.start();

        synchronized (obj) {
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    try {
                        /**
                         * 当前线程里调用其它线程myThread的join方法
                         * 当前线程main进入WAITING/TIMED_WAITING状态
                         * 当前线程main不会释放已经持有obj的对象锁
                         *
                         * 线程myThread执行完毕或者millis时间到
                         * 当前线程进入就绪状态
                         *
                         * 由于myThread需要先获取obj对象锁才能执行
                         * 所以程序运行到这里会被卡死
                         *
                         * jstack后发现，这种情况不算死锁
                         */
                        myThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -> " +  i);
            }
        }
    }
}

class MyThread extends Thread {
    private String name;
    private Object obj;

    public MyThread(String name) {
        this.name = name;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (obj) {
            for (int i = 0; i < 10; i++) {
                System.out.println(name + i);
            }
        }
    }
}
