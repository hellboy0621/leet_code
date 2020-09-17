package com.transformers.solution.string;

public class Solution344 {
    public void reverseString(char[] s) {
        int head = 0;
        int tail = s.length - 1;
        while (head < tail) {
            char tmp = s[head];
            s[head] = s[tail];
            s[tail] = tmp;
            head++;
            tail--;
        }
    }
}
