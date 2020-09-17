package com.transformers.solution.bitoperation;

public class Solution191 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int result = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                result++;
            }
            mask <<= 1;
        }
        return result;
    }

    public int hammingWeight2(int n) {
        int result = 0;
        // 此处判断条件不能为n>0，存在n为负数的情况
        while (n != 0) {
            // 对于任意一个数，将n和n-1进行&运算，我们都可以把n中最低位的1变成0
            n &= (n - 1);
            result++;
        }
        return result;
    }
}
