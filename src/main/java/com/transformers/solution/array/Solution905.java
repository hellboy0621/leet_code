package com.transformers.solution.array;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution905 {

    /**
     * 插入排序
     *
     * @param arr
     */
    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    int tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        new Solution905().insertSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] a = new int[]{0, 1, 2};
        new Solution905().sortArrayByParity(a);
        System.out.println(Arrays.toString(a));

        System.out.println(new Solution905().sumNums(5));
        System.out.println(new Solution905().sumNums2(6));
        System.out.println(new Solution905().sumNums3(7));
    }

    public int[] sortArrayByParity(int[] A) {
        int j = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                j++;
            }
        }
        return A;
    }

    /**
     * 将递归的返回条件取非然后作为&&的第一个条件，递归主体转换为第二个条件语句
     *
     * @param n
     * @return
     */
    public int sumNums(int n) {
        boolean b = n > 0 && (n += sumNums(n - 1)) > 0;
        System.out.println(b);
        return n;
    }

    public int sumNums2(int n) {
        return ((int) (Math.pow(n, 2) + n)) >> 1;
    }

    public int sumNums3(int n) {
        return IntStream.range(1, n + 1).sum();
    }

}
