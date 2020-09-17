package com.transformers.solution.array;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && i != j) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("no two sum solution.");
    }

    // 空间换时间
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int a = target - nums[i];
            if (map.containsKey(a)) {
                return new int[]{map.get(a), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no two sum solution.");
    }
}
