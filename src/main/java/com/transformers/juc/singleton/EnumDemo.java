package com.transformers.juc.singleton;

import java.lang.reflect.Constructor;

public enum EnumDemo {

    INSTANCE;

    public static EnumDemo getInstance() {
        return INSTANCE;
    }

}

class Test {
    public static void main(String[] args) throws Exception {
        System.out.println(EnumDemo.INSTANCE == EnumDemo.INSTANCE);

        // 使用反射
        // Exception in thread "main" java.lang.NoSuchMethodException: com.transformers.juc.singleton.EnumDemo.<init>()
//        Constructor<EnumDemo> declaredConstructor = EnumDemo.class.getDeclaredConstructor(null);

        // Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        // protected Enum(String name, int ordinal)
        Constructor<EnumDemo> declaredConstructor = EnumDemo.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumDemo instance = declaredConstructor.newInstance();
        EnumDemo instance2 = declaredConstructor.newInstance();
        System.out.println(instance);
        System.out.println(instance2);
    }
}
