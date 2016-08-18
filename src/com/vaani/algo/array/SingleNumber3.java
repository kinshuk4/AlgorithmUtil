package com.vaani.algo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given 2*n + 2 numbers, every numbers occurs twice except two, find them.
 * <p>
 * Example
 * Given [1,2,2,3,4,4,5,3] return 1 and 5
 * <p>
 * Challenge
 * O(n) time, O(1) extra space.
 */
public class SingleNumber3 {
    /**
     * @param A : An integer array
     * @return : Two integers
     * <p>
     * Slightly better solution: http://blog.xiaohuahua.org/2015/01/22/lintcode-single-number-iii/
     */
    public static List<Integer> singleNumberIII(int[] A) {
        int xor = 0;
        for (int i = 0; i < A.length; i++) {
            xor ^= A[i];
        }

        int mark = 1;
        while ((mark & xor) == 0) {
            mark <<= 1;
        }

        int num1 = 0, num2 = 0;
        for (int i = 0; i < A.length; i++) {
            if ((mark & A[i]) == 0) {
                num1 ^= A[i];
            } else {
                num2 ^= A[i];
            }
        }

        List<Integer> result = new ArrayList<Integer>();
        result.add(num1);
        result.add(num2);
        return result;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 2, 3, 4, 4, 5, 3};
        System.out.println(singleNumberIII(A));
    }
}
