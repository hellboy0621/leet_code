package com.transformers.solution.binarysearch;

public class Solution278 extends VersionControl {

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    @Override
    boolean isBadVersion(int version) {
        return false;
    }
}

abstract class VersionControl {
    abstract boolean isBadVersion(int version);
}
