package com.transformers.advanced.week04.class08.collection;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>(Comparator.reverseOrder());
        treeMap.put(3, "val");
        treeMap.put(2, "val");
        treeMap.put(5, "val");
        treeMap.put(1, "val");
        treeMap.put(4, "val");
        // {5=val, 4=val, 3=val, 2=val, 1=val}
        System.out.println(treeMap);

        TreeMap<Integer, String> map = new TreeMap<>(Comparator.naturalOrder());
        map.putAll(treeMap);
        // {1=val, 2=val, 3=val, 4=val, 5=val}
        System.out.println(map);
    }
}
