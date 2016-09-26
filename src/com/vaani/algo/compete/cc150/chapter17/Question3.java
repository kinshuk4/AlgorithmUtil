package com.vaani.algo.compete.cc150.chapter17;

/**
 * Design an algorithm which counts the number of trailing zeros in n factorial.
 */
// O(1) space, O(nlgn) time
public class Question3 {

    public int numZeros(int n) {
        //  write implementation here
        int numOfTwo = 0;
        int numOfFive = 0;
        for (int i = 0; i <= n; ++i) {
            int num = i;
            while (num > 0) {
                if (num % 2 == 0) {
                    ++numOfTwo;
                    num >>= 1;
                }
                if (num % 5 == 0) {
                    ++numOfFive;
                    num /= 5;
                }
                num /= 10;
            }
        }
        return Math.min(numOfTwo, numOfFive);
    }

}

