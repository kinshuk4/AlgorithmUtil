package com.vaani.algo.paradigm.dp;

import java.util.ArrayList;
import java.util.List;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fstrings%2FLongestDecreasingSubSequence.java
public class LongestDecreasingSubSequence {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 19, 12, 5, 4, 2, 3, 7, 9, 10, 6, 8, 3, 4, 3, 2, 1};
        LongestDecreasingSubSequence longestSubSequence = new LongestDecreasingSubSequence();
        List<Integer> longestDecreaseSubSequence = longestSubSequence.getLongestDecreaseSubSequence(nums);
        System.out.println(longestDecreaseSubSequence);
        List<Integer> longestIncreaseSubSequence = longestSubSequence.getLongestIncreaseSubSequence(nums);
        System.out.println(longestIncreaseSubSequence);

    }


    public List<Integer> getLongestDecreaseSubSequence(int[] nums) {
        int[] dp = new int[nums.length];
        int[] priors = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        List<Integer> result = new ArrayList<>();
        int max = 0;
        int endIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                dp[i] = dp[i - 1] + 1;
                priors[i] = i - 1;
            } else {
                for (int j = 0; j < i; j++) {
                    if (nums[j] > nums[i] && (dp[j] + 1) > dp[i]) {
                        dp[i] = dp[j] + 1;
                        priors[i] = j;
                    }
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                endIndex = i;
            }
        }

        while (max > 0) {
            result.add(0, nums[endIndex]);
            endIndex = priors[endIndex];
            max--;
        }
        return result;
    }


    public List<Integer> getLongestIncreaseSubSequence(int[] nums) {
        int[] dp = new int[nums.length];
        int[] priors = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        List<Integer> result = new ArrayList<>();
        int max = 0;
        int endIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                dp[i] = dp[i - 1] + 1;
                priors[i] = i - 1;
            } else {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i] && (dp[j] + 1) > dp[i]) {
                        dp[i] = dp[j] + 1;
                        priors[i] = j;
                    }
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                endIndex = i;
            }
        }
        //
        while (max > 0) {
            result.add(0, nums[endIndex]);
            endIndex = priors[endIndex];
            max--;
        }
        return result;

    }


}