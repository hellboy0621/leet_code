package com.transformers.solution.binarysearch;

public class Solution69 {

    public int mySqrt(int x) {
        if (x == 0) return 0;
        long left = 1;
        long right = x / 2;
        while (left < right) {
//            long mid = (left + right) / 2 + 1;
//            long mid = left + (right - left + 1) / 2;
            long mid = (left + right + 1) >> 1;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return (int) left;
    }

    public int mySqrt2(int x) {
        long left = 0;
        long right = x;
        while (left <= right) {
            long mid = (left + right) >> 1;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int) right;
    }

    public static void main(String[] args) {
        new Solution69().mySqrt2(8);
    }

}
