package com.transformers.solution.binarysearch;

public class Solution875 {

    public int minEatingSpeed(int[] piles, int H) {
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            if (pile > right) {
                right = pile;
            }
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (canEat(piles, mid, H)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canEat(int[] piles, int speed, int h) {
        int sum = 0;
        for (int pile : piles) {
            sum += Math.ceil(pile * 1.0 / speed);
        }
        return sum <= h;
    }

}
