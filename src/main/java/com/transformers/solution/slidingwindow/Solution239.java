package com.transformers.solution.slidingwindow;

import java.util.ArrayDeque;

public class Solution239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;
        if (length * k == 0) {
            return null;
        }
        int[] ret = new int[length - k + 1];
        for (int i = 0; i < length - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            ret[i] = max;
        }
        return ret;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int length = nums.length;
        if (length * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        int maxIndex = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < k; i++) {
            cleanDeque(deque, nums, i, k);
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int[] result = new int[length - k + 1];
        result[0] = nums[maxIndex];

        // build result
        for (int i = k; i < length; i++) {
            cleanDeque(deque, nums, i, k);
            result[i - k + 1] = nums[deque.getFirst()];
        }
        return result;
    }

    private void cleanDeque(ArrayDeque<Integer> deque, int[] nums, int i, int k) {
        // remove indexes of elements not from sliding window
        handleHead(deque, i, k);
        // remove from deque indexes of all elements
        // which are smaller than current element nums[i]
        while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
            deque.removeLast();
        }
        deque.addLast(i);
    }

    // refactor
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int len = nums.length;
        if (len * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            handleTail(deque, nums, i);
        }
        int[] result = new int[len - k + 1];
        result[0] = nums[deque.getFirst()];
        for (int i = k; i < len; i++) {
            handleHead(deque, i, k);
            handleTail(deque, nums, i);
            result[i - k + 1] = nums[deque.getFirst()];
        }
        return result;
    }

    private void handleTail(ArrayDeque<Integer> deque, int[] nums, int i) {
        while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
            deque.removeLast();
        }
        deque.addLast(i);
    }

    private void handleHead(ArrayDeque<Integer> deque, int i, int k) {
        if (!deque.isEmpty() && deque.getFirst() == i - k) {
            deque.removeFirst();
        }
    }


}
