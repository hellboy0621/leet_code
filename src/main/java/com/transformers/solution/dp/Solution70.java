package com.transformers.solution.dp;

import java.util.HashMap;
import java.util.Map;

public class Solution70 {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] result = new int[n];
        result[0] = 1;
        result[1] = 2;
        for (int i = 2; i < n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n - 1];
    }
}
