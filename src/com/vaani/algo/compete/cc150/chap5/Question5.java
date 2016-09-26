package com.vaani.algo.compete.cc150.chap5;

/**
 * Write a function to determine the number of bits required to convert integer
 * A to integer B.
 */
public class Question5 {

    public int convertSteps(int A, int B) {
        // write implementation here.
        int dif = A ^ B;
        int numOf1 = 0;
        while (dif > 0) {
            ++numOf1;
            dif &= dif - 1;
        }
        return numOf1;
    }

}
