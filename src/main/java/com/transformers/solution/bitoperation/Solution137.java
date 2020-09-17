package com.transformers.solution.bitoperation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution137 {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        for (Integer i : map.keySet()) {
            if (map.get(i) == 1) {
                return i;
            }
        }
        return 0;
    }

    public int singleNumber2(int[] nums) {
        return (int) ((Arrays.stream(nums).asLongStream().distinct().sum() * 3
                - Arrays.stream(nums).asLongStream().sum()) / 2);
    }

    public int singleNumber3(int[] nums) {
        int number = 0, res = 0;
        for (int i = 0; i < 64; i++) {
            number = 0;
            for (int j = 0; j < nums.length; j++) {
                // 通过右移i位的方式，计算每一位1的个数
                number += (nums[j] >> i) & 1;
            }
            res |= number % 3 << i;
        }
        return res;
    }

    public int singleNumber4(int[] nums) {
        int a = 0, b = 0, tmp = 0;
        for (int next : nums) {
            tmp = (a & ~next) | (b & next);
            b = (~a & ~b & next) | (b & ~next);
            a = tmp;
        }
        return b;
    }

    public int singleNumber5(int[] nums) {
        int a = 0, b = 0, tmp = 0;
        for (int next : nums) {
            b = (b ^ next) & ~a;
            a = (a ^ next) & ~b;
        }
        return b;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 3, 2};
        System.out.println(new Solution137().singleNumber4(nums));
    }
}
