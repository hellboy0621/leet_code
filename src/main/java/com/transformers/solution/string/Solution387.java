package com.transformers.solution.string;

public class Solution387 {
    public int firstUniqChar(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == arr[s.charAt(i) - 'a']) {
                return i;
            } else {
                arr[s.charAt(i) - 'a'] = -1;
            }
        }
        return -1;
    }
}
