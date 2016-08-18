package com.vaani.algo.array;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * <p>
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * Created by Xiaomeng on 7/20/2014.
 */
public class SingleNumber2 {
    public static int singleNumber(int[] A) {
        int[] bitVector = new int[32];
        int result = 0;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < A.length; j++) {
                bitVector[i] += (A[j] >> i) & 1;
            }
            result |= (bitVector[i] % 3) << i;
        }
        return result;
    }

    public static int singleNumber2(int[] A) {
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < A.length; i++) {
            twos |= (A[i] & ones);
            ones ^= A[i];
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }

    public static void main(String[] args) {
        int[] A = {2, 2, 3, 2};
        System.out.println(singleNumber(A));
    }
}
