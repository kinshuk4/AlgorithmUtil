/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 13/10/2015
 * 
 * Problem
 * 
 * Write a function which performs division of big integer and an integer.
 * 
 * Input
 *
12345678901234567890
1000
 * 
 * Output
 * 
12345678901234567
 * 
 */

package com.vaani.algo.compete.mensikovbook;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

public class BigDecimalDivision {

    private static String div(int[] a, int b) {
        int rem = 0;
        String res = "";
        for (int i = 0; i < a.length; ++i) {
            rem = rem * 10 + a[i];
            res += rem / b;
            rem = rem % b;
        }
        return new BigInteger(res).toString();
    }
    
    public static void main(String[] args) {
        assertEquals("12345678901234567", div("12345678901234567890".chars().map(ch -> ch - '0').toArray(), 1000));
    }

}
