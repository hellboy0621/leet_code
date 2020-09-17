package com.transformers.solution.dp;

public class Solution53 {
    public int maxSubArray(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        // dp[i]表示以nums[i]结尾的连续子数组的最大和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 如果dp[i-1]为负数，nums[i]大；如果dp[i-1]为正数，dp[i-1]+nums[i]大。
            dp[i] = Math.max(dp[i - 1], nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
