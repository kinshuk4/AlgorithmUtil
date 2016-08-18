package com.vaani.algo.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 一个数组,一个target,求所有的pairs, array[i] - array[j] = k
 * k is positive
 * 降低时间开销,怎么搞?
 * <p>
 * Reference: http://www.geeksforgeeks.org/count-pairs-difference-equal-k/
 */
public class FindPair {
    public static int findPairs(int[] arr, int k) {
        Arrays.sort(arr);
        int start = 0, end = 0;
        int len = arr.length;
        int count = 0;
        while (end < len) {
            if (arr[end] - arr[start] > k) {
                start++;
            } else if (arr[end] - arr[start] < k) {
                end++;
            } else {
                count++;
                start++;
                end++;
            }
        }
        return count;
    }

    public static int findPairs2(int[] arr, int k) {
        int count = 0;
        Set<Integer> set = new HashSet<Integer>();
        for (int num : arr) {
            if (!set.contains(num)) set.add(num);
        }

        for (int num : arr) {
            int target = num + k;
            if (set.contains(target)) {
                System.out.println(num + " " + target);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {8, 12, 16, 4, 0, 20};
        System.out.println(findPairs(arr, 4));
        System.out.println(findPairs2(arr, 4));
    }
}
