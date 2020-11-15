package com.transformers.advanced.week04.class08.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        System.out.println("test HashMap");
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("name1", "josan1");
        hashMap.put("name2", "josan2");
        hashMap.put("name3", "josan3");
        Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + " -> " + next.getValue());
        }

        System.out.println("test LinkedHashMap");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("name1", "josan1");
        linkedHashMap.put("name2", "josan2");
        linkedHashMap.put("name3", "josan3");
        Iterator<Map.Entry<String, String>> iter = linkedHashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> next = iter.next();
            System.out.println(next.getKey() + " -> " + next.getValue());
        }
        /**
         * test HashMap
         * name3 -> josan3
         * name2 -> josan2
         * name1 -> josan1
         * test LinkedHashMap
         * name1 -> josan1
         * name2 -> josan2
         * name3 -> josan3
         */
    }
}
