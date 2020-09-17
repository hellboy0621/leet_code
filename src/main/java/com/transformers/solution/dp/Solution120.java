package com.transformers.solution.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution120 {
    public static void main(String[] args) {
        // [[2],[3,4],[6,5,7],[4,1,8,3]]
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
        new Solution120().minimumTotal(triangle);
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) {
            return 0;
        }
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        List<List<Integer>> dp = new ArrayList<>(triangle.size());
        for (int i = 0; i < triangle.size(); i++) {
            dp.add(new ArrayList<>(triangle.get(i).size()));
        }
        int result = Integer.MAX_VALUE - 1;
        dp.get(0).add(triangle.get(0).get(0));
        dp.get(1).add(triangle.get(1).get(0) + triangle.get(0).get(0));
        dp.get(1).add(triangle.get(1).get(1) + triangle.get(0).get(0));

        for (int i = 2; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    dp.get(i).add(dp.get(i - 1).get(j) + triangle.get(i).get(j));
                } else if (j == triangle.get(i).size() - 1) {
                    dp.get(i).add(dp.get(i - 1).get(j - 1) + triangle.get(i).get(j));
                } else {
                    dp.get(i).add(Math.min(dp.get(i - 1).get(j), dp.get(i - 1).get(j - 1)) + triangle.get(i).get(j));
                }
            }
        }
        for (int i = 0; i < dp.get(dp.size() - 1).size(); i++) {
            result = Math.min(result, dp.get(dp.size() - 1).get(i));
        }
        return result;
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null) {
            return 0;
        }
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int result = Integer.MAX_VALUE - 1;
        triangle.get(0).set(0, triangle.get(0).get(0));
        triangle.get(1).set(0, triangle.get(1).get(0) + triangle.get(0).get(0));
        triangle.get(1).set(1, triangle.get(1).get(1) + triangle.get(0).get(0));

        for (int i = 2; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    triangle.get(i).set(j, triangle.get(i - 1).get(j) + triangle.get(i).get(j));
                } else if (j == triangle.get(i).size() - 1) {
                    triangle.get(i).set(j, triangle.get(i - 1).get(j - 1) + triangle.get(i).get(j));
                } else {
                    triangle.get(i).set(j, Math.min(triangle.get(i - 1).get(j), triangle.get(i - 1).get(j - 1)) + triangle.get(i).get(j));
                }
            }
        }
        for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++) {
            result = Math.min(result, triangle.get(triangle.size() - 1).get(i));
        }
        return result;
    }

    public int minimumTotal(int[][] triangle) {
        if (triangle == null) {
            return 0;
        }
        if (triangle.length == 1) {
            return triangle[0][0];
        }
        // dp[i][j]表示包含第i行j列元素的最小路径和
        int[][] dp = new int[triangle.length][];
        int result = Integer.MAX_VALUE - 1;
        for (int i = 0; i < triangle.length; i++) {
            dp[i] = new int[triangle[i].length];
        }
        dp[0][0] = triangle[0][0];
        dp[1][0] = triangle[1][0] + dp[0][0];
        dp[1][1] = triangle[1][1] + dp[0][0];
        for (int i = 2; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    // 最左边的元素只能从自己头顶而来
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else if (j == triangle[i].length - 1) {
                    // 最右边的元素只能从自己左上角而来
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }
            }
        }
        // 最后一行元素中，路径和最小的一个，就是我们的答案
        for (int i = 0; i < dp[dp.length - 1].length; i++) {
            result = Math.min(result, dp[dp.length - 1][i]);
        }
        return result;
    }
}
