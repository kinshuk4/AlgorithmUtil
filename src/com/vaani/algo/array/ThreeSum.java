package com.vaani.algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 * <p>
 * For example, given array S = {-1 0 1 2 -1 -4},
 * <p>
 * A solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 * <p>

 */
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            if (i != 0 && num[i] == num[i - 1]) continue;
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                if (num[start] + num[end] + num[i] > 0) {
                    end--;
                } else if (num[start] + num[end] + num[i] < 0) {
                    start++;
                } else {
                    List<Integer> triplet = new ArrayList<Integer>();
                    triplet.add(num[i]);
                    triplet.add(num[start]);
                    triplet.add(num[end]);
                    result.add(triplet);
                    start++;
                    end--;
                    while (start < end && num[start] == num[start - 1]) start++;
                    while (start < end && num[end] == num[end + 1]) end--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 0};
        List<List<Integer>> result = threeSum(nums);
        for (List<Integer> triplet : result) {
            System.out.println(triplet);
        }
    }
}
