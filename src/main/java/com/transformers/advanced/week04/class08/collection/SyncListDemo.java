package com.transformers.advanced.week04.class08.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SyncListDemo {
    public static void main(String[] args) {
        List<Integer> list0 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 8);
        // 可以修改内容，不能add/remove，否则抛异常java.lang.UnsupportedOperationException
        list0.set(8, 9);
//        list0.add(1);
//        list0.remove(1);

        List<Integer> list1 = new ArrayList<>();
        list1.addAll(list0);

        List<Integer> list2 = Collections.synchronizedList(list1);

        System.out.println(Arrays.toString(list2.toArray()));
        Collections.shuffle(list2);
        System.out.println(Arrays.toString(list2.toArray()));

        // 不再修改
        List<Integer> list3 = Collections.unmodifiableList(list2);
        // class java.util.Collections$UnmodifiableRandomAccessList
        System.out.println(list3.getClass());

//        list3.set(8, 10);
        // Exception in thread "main" java.lang.UnsupportedOperationException
//        System.out.println(Arrays.toString(list3.toArray()));

//        list3.add(11);
        // Exception in thread "main" java.lang.UnsupportedOperationException
//        System.out.println(Arrays.toString(list3.toArray()));
    }
}
