package com.transformers.solution.bitoperation;

public class Solution136 {
    public int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution136().singleNumber(new int[]{2, 1, 2}));
    }
}
