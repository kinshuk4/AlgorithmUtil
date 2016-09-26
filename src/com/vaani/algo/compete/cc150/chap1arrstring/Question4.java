package com.vaani.algo.compete.cc150.chap1arrstring;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume
 * that the string has sufficient space at the end of the string to hold the
 * additional characters, and that you are given the "true" length of the
 * string.
 */
//  O(n) space, O(n) time
public class Question4 {

    public char[] replace(char[] input, int length) {

        int pos = input.length - 1;
        //  traverse backwards
        for (int i = length - 1; i >= 0; --i) {
            if (input[i] == ' ') {
                input[pos--] = '0';
                input[pos--] = '2';
                input[pos--] = '%';
            } else {
                input[pos--] = input[i];
            }
        }

        return input;
    }

}

