package com.transformers.solution.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.isEmpty() || p == null || p.isEmpty() || s.length() < p.length()) {
            return result;
        }
        int[] sArr = new int[26], pArr = new int[26];
        // init
        for (int i = 0; i < p.length(); i++) {
            sArr[s.charAt(i) - 'a']++;
            pArr[p.charAt(i) - 'a']++;
        }
        int left = 0, right = p.length() - 1;
        if (Arrays.equals(sArr, pArr)) {
            result.add(left);
        }
        while (right < s.length() - 1) {
            sArr[s.charAt(left++) - 'a']--;
            sArr[s.charAt(++right) - 'a']++;
            if (Arrays.equals(sArr, pArr)) {
                result.add(left);
            }
        }
        return result;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.isEmpty() || p == null || p.isEmpty() || s.length() < p.length()) {
            return result;
        }
        int[] sArr = new int[26], pArr = new int[26];
        // init
        for (int i = 0; i < p.length(); i++) {
            sArr[s.charAt(i) - 'a']++;
            pArr[p.charAt(i) - 'a']++;
        }
        int left = 0, right = p.length();
        while (right < s.length()) {
            if (Arrays.equals(sArr, pArr)) {
                result.add(left);
            }
            sArr[s.charAt(left) - 'a']--;
            sArr[s.charAt(right) - 'a']++;
            left++;
            right++;
        }
        if (Arrays.equals(sArr, pArr)) {
            result.add(left);
        }
        return result;
    }

    public static void main(String[] args) {
        new Solution438().findAnagrams("abab", "ab");
    }
}
