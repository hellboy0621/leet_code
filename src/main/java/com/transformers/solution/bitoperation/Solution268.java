package com.transformers.solution.bitoperation;

import java.util.Arrays;

public class Solution268 {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int sum = (len + 1) * len / 2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    public int missingNumber2(int[] nums) {
        // 任何数与0异或还是这个数
        // 任何数与自身异或为0
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }
        return result ^ nums.length;
    }
}
