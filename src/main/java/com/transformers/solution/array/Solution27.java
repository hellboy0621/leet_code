package com.transformers.solution.array;

import java.util.Arrays;

public class Solution27 {
    public int removeElement(int[] nums, int val) {
        // 使用双指针，i为慢指针，j为快指针
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                // 如果不是要删除的元素，从前到后依次排列
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    public int removeElement2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                // 如果相等，跟最后一个元素交换位置，并把最后一个元素去除
                nums[i] = nums[n - 1];
                n--;
            } else {
                // 如果不是要删除的元素，数据不动
                i++;
            }
        }
        return i;
    }

    // 延伸
    // 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次
    public int removeElement3(int[] nums) {
        // 使用双指针，i为慢指针，j为快指针
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            // 如果不相等，给i+1后赋值
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
            // 如果相等，j++，进行后一个判断
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        new Solution27().removeElement3(nums);
        System.out.println(Arrays.toString(nums));
    }
}
