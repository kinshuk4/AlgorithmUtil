package com.vaani.algo.compete.cc150.chap5;


/**
 * You are given two 32-bit numbers, N and M, and two bit locations, i and j.
 * Write a method to insert M into N such that M starts at bit j and ends at bit
 * i. You can assume that the bits j through i have enough space to fit all of
 * M. That is, if M = 10011, you can assume that there are at least 5 bits
 * between j and i. You would not, for example, have j = 3 and i = 2, because M
 * could not fully fit between bit 3 and bit 2.
 * <p>
 * EXAMPLE Input: N = 10000000000, M = 10011, i = 2, j = 6 Output: N =
 * 1001001100
 */
// O(n) space, O(1) time
public class Question1 {

    public int insert(int N, int M, int i, int j) {
        int mask = ~0;
        // clear bits
        for (int b = i; b <= j; ++b) {
            N &= ~(1 << b);
        }
        // set bits
        N &= mask;
        N |= M << i;
        return N;
    }

}
