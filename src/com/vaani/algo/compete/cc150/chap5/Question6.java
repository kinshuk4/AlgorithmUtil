package com.vaani.algo.compete.cc150.chap5;

/**
 * Write a program to swap odd and even bits in an integer with as few
 * instructions as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3
 * are swapped, and so on).
 */
public class Question6 {

    public int numInstructions(int num) {
        // write implementation here.
        return num & 0xaaaaaaaa >> 1 | num & 0x55555555 << 1;
    }

}
