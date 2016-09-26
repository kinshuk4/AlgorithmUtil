package com.vaani.algo.compete.cc150.chapter17;

/**
 * Write a method which finds the maximum of two numbers. You should not use
 * if-else or any other comparison operator.
 */
// O(1) space, O(1) time
public class Question4 {

    public int max(int a, int b) {
        //  write implementation here
        int signA = sign(a);
        int signB = sign(b);
        int signAB = sign(a - b);

        // if a and b are with different signs, branch = 1
        int branch = (signA ^ signB);

        // if a and b are with different signs, if a is negative, return b, else return a
        int case1 = signA * a + (signA ^ 1) * b;

        // if a and b are with same signs, if sign(a - b) == 1 return a, else return b
        int case2 = signAB * a + (signAB ^ 1) * b;

        return branch * case1 + (branch ^ 1) * case2;
    }

    // if num is positive, return 1, else return 0
    private int sign(int num) {
        return (num >> 31 & 0x1) ^ 1;
    }

}
