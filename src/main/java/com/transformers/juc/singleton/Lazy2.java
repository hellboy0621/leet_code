package com.transformers.juc.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

// 懒汉式
public class Lazy2 {

    // 使用反射破坏单例
    // 先使用构造方法
    // 再使用类对象成员变量
    public static void main(String[] args) throws Exception {
//        Lazy2 instance = Lazy2.getInstance();
        Constructor<Lazy2> constructor = Lazy2.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Lazy2 instance = constructor.newInstance();

        Field biaozhi = Lazy2.class.getDeclaredField("biaozhi");
        biaozhi.setAccessible(true);
        biaozhi.set(instance, false);

        // 探究反射的newInstancce方法源码
        Lazy2 instance2 = constructor.newInstance();
        System.out.println(instance);
        System.out.println(instance2);
    }

    // 使用一个加密的变量标识位
    private static boolean biaozhi = false;

    private Lazy2() {
        // 增加判断
//        synchronized (Lazy2.class) {
//            if (lazy != null) {
//                throw new IllegalArgumentException("Cannot reflectively create singleton objects");
//            }
//        }
//        System.out.println("Lazy.Lazy");
        if (!biaozhi) {
            biaozhi = true;
        } else {
            throw new IllegalArgumentException("Cannot reflectively create singleton objects");
        }
    }

    private static volatile Lazy2 lazy;

    /**
     * 1.分配内存空间
     * 2.执行构造方法，初始化对象
     * 3.把这个对象指向这个空间
     * <p>
     * 如果指令重排，导致线程A 132
     * 线程B在线程A执行到2之前，获取到CPU时间片，此时拿到的lazy对象还没有初始化
     * 需要使用volatile禁止指令重排
     */
    public static Lazy2 getInstance() {
        // 双重检测锁 懒汉式单例 DCL懒汉式单例
        if (lazy == null) {
            synchronized (Lazy2.class) {
                if (lazy == null) {
                    lazy = new Lazy2();
                }
            }
        }
        return lazy;
    }

}
