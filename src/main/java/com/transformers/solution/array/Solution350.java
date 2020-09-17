package com.transformers.solution.array;

import java.util.*;

public class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                nums1[k++] = nums1[i];
                i++;
                j++;
            }
        }
        int[] result = new int[k];
        System.arraycopy(nums1, 0, result, 0, k);
        return result;
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.get(nums1[i]) == null) {
                map.put(nums1[i], 1);
            } else {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            }
        }
        int k = 0;
        for (int i = 0; i < nums2.length; i++) {
            int nums2i = nums2[i];
            if (map.get(nums2i) != null) {
                nums2[k++] = nums2i;
                if (map.get(nums2i) == 1) {
                    map.remove(nums2i);
                } else {
                    map.put(nums2i, map.get(nums2i) - 1);
                }
            }
        }
        int[] result = new int[k];
        System.arraycopy(nums2, 0, result, 0, k);
        return result;
    }

    public int[] intersect3(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.get(nums1[i]) == null) {
                map.put(nums1[i], 1);
            } else {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            }
        }
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map.get(nums2[i]) != null) {
                resultList.add(nums2[i]);
                if (map.get(nums2[i]) == 1) {
                    map.remove(nums2[i]);
                } else {
                    map.put(nums2[i], map.get(nums2[i]) - 1);
                }
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

}
