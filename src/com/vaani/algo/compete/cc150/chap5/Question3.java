package com.vaani.algo.compete.cc150.chap5;

/**
 * Given a positive integer, print the next smallest and the next largest number
 * that have the same number of 1 bits in their binary representation.
 */
// O(n) space, O(n) time
public class Question3 {

    public int prev(int num) {
        int c1 = 0;
        int c0 = 0;
        int tmp = num;
        // count
        while ((tmp != 0) && ((tmp & 1) == 1)) {
            ++c1;
            tmp >>= 1;
        }
        if (tmp == 0) { // if num is 111...111
            return -1;
        }
        while ((tmp != 0) && ((tmp & 1) == 0)) {
            ++c0;
            tmp >>= 1;
        }
        int p = c0 + c1;
        num &= ~(1 << p); // set p as 0
        num |= 1 << p - 1; // set all bits right to p to 1
        num &= ~0 << (c0 - 1); // set rightmost c0 - 1 bits as zeros
        return num;
    }

    public int next(int num) {
        // count the training zeros and the number of ones following (on the left) them
        int c0 = 0;
        int c1 = 0;
        int tmp = num;
        // count c0 and c1
        while ((tmp != 0) && ((tmp & 1) == 0)) {
            ++c0;
            tmp >>= 1;
        }

        while ((tmp != 0) && ((tmp & 1) == 1)) {
            ++c1;
            tmp >>= 1;
        }
        if (c0 + c1 == 31 || c0 + c1 == 0) {  // if num = 1...10...0
            return -1;
        }
        int p = c0 + c1; // the position of zero follows the c1
        // set all bits after p to 0 and set the rightmost c1 - 1 bits to 1
        num |= (1 << p);
        num &= ~((1 << p) - 1); // clear all bits to the right of p
        num |= (1 << (c1 - 1)) - 1; // insert c1 - 1 ones to the rightmost
        return num;
    }

}

