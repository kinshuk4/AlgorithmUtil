/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 13/08/2015
 * 
 * Hacker rank
 * New York Life Hackathon
 * Problem: Count strings
 * Status: accepted
 * 
 * Alice has found a way to assign a numerical value to any string of lowercase characters. 
 * To assign the value, Alice takes the numerical value of each character, and sums their 
 * squares. The numerical value of a character depends on its position in the alphabet, so 
 * 'a'=1, 'b'=2, 'e'=5, 'z'=26 etc. In this scheme, the value of the string "ace" will be:  
 * 12 + 32 + 52 = 35. The value of "cae" or "aec" is also 35.
 * Alice wants to find out the number of strings of length l which have value v.
 * Help Alice by completing a function countStrings that takes l and v as parameters and 
 * returns the answer to the question. As the answer can be very large, return the result 
 * modulo 10,000,007.
 * 
 * Constraints:
 * 1 ≤ l ≤ 100
 * 1 ≤ v ≤ 10000
 * 
 * Input Format
 * 
 * Length l and value v.
 * 
 * Output Format
 * 
 * Number of possible strings.
 * 
 * Sample Input
 * 
l=2 
v=13
 * 
l=3
v=29
 *
 * Sample Output
 * 
2
 *
6
 *
 * The two strings "bc" and "cb" have a value of 2^2 + 3^2 = 13
 * All six the permutation of "bcd" have value 29.
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.newyorklife;

import org.junit.Assert;


public class CountStrings {

    static int[] NSQR = new int[26];
    static int[] W = new int[10_000];
    
    static int countStrings(int l, int v) {
        for (int i = 1; i < 27; ++i) {
            NSQR[i - 1] = i * i;
            W[NSQR[i - 1] - 1] = 1;
        }
        return countStrings_(l - 1, v);
    }
    
    static int countStrings_(int l, int v) {
        if (l == 0)
            return W[v - 1];
        int[] newW = new int[W.length];
        for (int w = 0; w < v; ++w) {
            for (int c = 0; c < 26; ++c) {
                int pw = w - NSQR[c];
                if (pw < 0) continue;
                newW[w] += W[pw];
                newW[w] %= 10_000_007;
            }
        }
        W = newW;
        return countStrings_(l - 1, v);
    }
    
    public static void main(String[] args) {
        Assert.assertEquals(2, countStrings(2, 13));
        Assert.assertEquals(6, countStrings(3, 29));
    }
    
}
