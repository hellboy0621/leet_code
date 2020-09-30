package com.transformers.juc.stream;

import java.util.Arrays;
import java.util.List;

public class Demo {
    // id是偶数
    // 年龄大于23
    // 用户名转换为大写
    // 用户名字母倒序
    // 输出一个
    public static void main(String[] args) {
        User user1 = new User(1, "a", 21);
        User user2 = new User(2, "b", 22);
        User user3 = new User(3, "c", 23);
        User user4 = new User(4, "d", 24);
        User user5 = new User(6, "e", 25);
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5);
        list.stream()
                .filter((user) -> user.getId() % 2 == 0)
                .filter((user) -> user.getAge() > 23)
                .map((user) -> user.getName().toUpperCase())
                .sorted((u1, u2) -> {
                    return u2.compareTo(u1);
                })
                .limit(1)
                .forEach(System.out::println);
    }
}
