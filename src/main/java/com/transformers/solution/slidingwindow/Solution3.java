package com.transformers.solution.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int result = 0;
        int[] charIndex = new int[256];
        for (int left = 0, right = 0; right < n; right++) {
            char c = s.charAt(right);
            // 一旦发现跟前面有相同的字符，直接将左指针移动到相同字符的最右侧位置
            left = Math.max(charIndex[c], left);
            result = Math.max(result, right - left + 1);
            // charIndex记录每个字符在字符串里的索引，从1开始
            charIndex[c] = right + 1;
        }
        return result;
    }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int left = 0, right = 0; right < n; right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(map.get(c), left);
            }
            result = Math.max(result, right - left + 1);
            map.put(c, right + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().lengthOfLongestSubstring("pwwkew"));
    }
}
