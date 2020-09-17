package com.transformers.solution.dp;

public class Solution300 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        // dp[i]表示以nums[i]结尾的长升序子序列的长度
        int[] dp = new int[nums.length];
        int result = 1;
        for (int i = 0; i < nums.length; i++) {
            // 如果nums[i]比前面所有数都小，dp[i]=1，即只有自身
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 如果nums[i]大于nums[j]，需要取出所有最大的
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
