package com.transformers.solution.array;

public class Solution1094 {

    public boolean carPooling(int[][] trips, int capacity) {
        int[] nums = new int[1001];
        // 构造差分数组工具类
        Difference diff = new Difference(nums);

        for (int[] trip : trips) {
            // 乘客数量
            int numPassengers = trip[0];
            // 上客站
            int from = trip[1];
            // 下客站
            // trip[2] 站乘客已经下车了，所以还在车上最后一站是 trip[2] - 1
            int to = trip[2] - 1;
            diff.increment(from, to, numPassengers);
        }

        int[] res = diff.result();
        for (int i = 0; i < res.length; i++) {
            if (res[i] > capacity) {
                return false;
            }
        }
        return true;
    }

    // 差分数组工具类
    class Difference {
        private int[] diff;

        // 输⼊⼀个初始数组，区间操作将在这个数组上进⾏
        public Difference(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            diff = new int[nums.length];
            // 根据初始数组构造差分数组
            diff[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        // 给闭区间 [i, j] 增加 val（可以是负数）
        public void increment(int i, int j, int val) {
            diff[i] += val;
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        // 返回结果数组
        public int[] result() {
            int[] result = new int[diff.length];
            //  根据差分数组构造结果数组
            result[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                result[i] = result[i - 1] + diff[i];
            }
            return result;
        }
    }

}
