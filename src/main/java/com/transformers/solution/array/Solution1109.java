package com.transformers.solution.array;


public class Solution1109 {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];
        Difference diff = new Difference(result);
        for (int[] booking : bookings) {
            int start = booking[0] - 1;
            int end = booking[1] - 1;
            int seats = booking[2];
            diff.increment(start, end, seats);
        }
        return diff.result();
    }

    // 差分数组工具类
    class Difference {
        private int[] diff;

        public Difference(int[] nums) {
            if (nums == null || nums.length <= 0) {
                throw new IllegalArgumentException("nums can't be null or empty");
            }
            diff = new int[nums.length];
            diff[0] = nums[0];
            for (int i = 1; i < diff.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        public void increment(int start, int end, int value) {
            diff[start] += value;
            if (end + 1 < diff.length) {
                diff[end + 1] -= value;
            }
        }

        public int[] result() {
            int[] result = new int[diff.length];
            result[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                result[i] = result[i - 1] + diff[i];
            }
            return result;
        }

    }

}
