package com.vaani.algo.misc;

/**
 * Given an array of positive numbers, find the maximum sum of a subsequence with the constraint that no 2 numbers in the sequence should be adjacent in the array.
 * So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10 7 should return 15 (sum of 3, 5 and 7).
 * Answer the question in most efficient way.
 */
public class NoAdjacentMaxSum {
    /**
     * DP
     * Time: O(n)
     * Space: O(n)
     */
    public static int getMaxSum(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];

        int[] memo = new int[len];
        memo[0] = nums[0];
        memo[1] = Math.max(nums[1], memo[0]);

        for (int i = 2; i < len; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + nums[i]);
        }
        return memo[len - 1];
    }

    /**
     * DP
     * Time: O(n)
     * Space: O(1)
     */
    public static int getMaxSum2(int[] nums) {
        int a = nums[0];
        int b = Math.max(nums[1], nums[0]);

        int len = nums.length;
        for (int i = 2; i < len; i++) {
            int c = Math.max(b, a + nums[i]);
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        int[] num1 = {3, 2, 7, 10};
        int[] num2 = {3, 2, 5, 10, 7};
        int[] num3 = {5, 5, 10, 40, 50, 35};
        System.out.println(getMaxSum(num1));
        System.out.println(getMaxSum(num2));
        System.out.println(getMaxSum(num3));

        System.out.println(getMaxSum2(num1));
        System.out.println(getMaxSum2(num2));
        System.out.println(getMaxSum2(num3));
    }
}
