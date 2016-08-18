package com.vaani.algo.array;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * For example:
 * Given array A = [2,3,1,1,4]
 * <p>
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * <p>
 * Created by Xiaomeng on 7/24/2014.
 */
public class JumpGame2 {
    /**
     * Greedy algorithm
     *
     * @param A
     * @return The minimum number of jumps
     */
    public static int jump(int[] A) {
        if (A.length == 1) return 0;
        int max = 0, count = 0, start = 0, end = 0;
        while (end < A.length) {
            count++;
            for (int i = start; i <= end; i++) {
                if (i + A[i] >= A.length - 1) return count;
                max = i + A[i] > max ? i + A[i] : max;
            }
            start = end + 1;
            end = max;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 1, 1, 1};
        System.out.println(jump(A));
    }
}
