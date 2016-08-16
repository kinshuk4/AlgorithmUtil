package com.vaani.algo.arrays;

/**
 *  Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 *  Each element in the array represents your maximum jump length at that position.
 *
 *  Determine if you are able to reach the last index.
 *
 *  For example:
 *  A = [2,3,1,1,4], return true.
 *
 *  A = [3,2,1,0,4], return false.
 *
 *  Created by Xiaomeng on 7/24/2014.
 */
public class JumpGame {
    public static boolean canJump(int[] A) {
        int max = 0;
        for(int i = 0; i < A.length; i++){
            if(i <= max){
                max = i + A[i] > max ? i + A[i] : max;
                if(max >= A.length - 1) return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        int[] A = {2,2,1,0,4,0,0,0};
        System.out.println(canJump(A));
    }
}
