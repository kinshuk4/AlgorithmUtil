package com.vaani.algo.array;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Created by Xiaomeng on 7/20/2014.
 */
public class SingleNumber {
    public int singleNumber(int[] A) {
        int result = 0;
        for(int i = 0; i < A.length; i++){
            result ^= A[i];
        }
        return result;
    }
}
