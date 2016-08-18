package com.vaani.algo.array.container;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * <p>
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * <p>
 * Created by Xiaomeng on 9/2/2014.
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater test = new TrappingRainWater();
        int[] A = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(test.trap(A));
    }

    public int trap(int[] A) {
        if (A.length <= 2) return 0;

        //Find left most height for each bar
        int[] left = new int[A.length];
        left[0] = 0;
        for (int i = 1; i < A.length; i++) {
            left[i] = Math.max(left[i - 1], A[i - 1]);
        }

        //Find right most height for each bar
        int[] right = new int[A.length];
        right[A.length - 1] = 0;
        for (int i = A.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], A[i + 1]);
        }

        int water = 0;
        for (int i = 0; i < A.length; i++) {
            if (Math.min(left[i], right[i]) - A[i] > 0) {
                water += Math.min(left[i], right[i]) - A[i];
            }
        }
        return water;
    }
}
