package com.transformers.juc.singleton;

// 懒汉式
public class Lazy {
    private Lazy() {
        System.out.println("Lazy.Lazy");
    }

    private static volatile Lazy lazy;

    /**
     * 1.分配内存空间
     * 2.执行构造方法，初始化对象
     * 3.把这个对象指向这个空间
     *
     * 如果指令重排，导致线程A 132
     * 线程B在线程A执行到2之前，获取到CPU时间片，此时拿到的lazy对象还没有初始化
     * 需要使用volatile禁止指令重排
     */
    public static Lazy getInstance() {
        // 双重检测锁 懒汉式单例 DCL懒汉式单例
        if (lazy == null) {
            synchronized (Lazy.class) {
                if (lazy == null) {
                    // new对象操作不是原子操作
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }
}
