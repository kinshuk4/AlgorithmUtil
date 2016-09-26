/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 30/07/2016
 * 
 * Hacker rank
 * World CodeSprint #5
 * Problem: Longest Increasing Subsequence Arrays
 * Status: timeout
 * 
 * We define the following:
 * 
 *  - A subsequence of an array is an ordered subset of the 
 * array's elements having the same sequential ordering as 
 * the original array. For example, the subsequences of 
 * array 1, 2, 3 are 1, 2, 3, 12, 13, 23, and 123.
 *  - The longest increasing subsequence of an array of 
 * numbers is the longest possible subsequence that can 
 * be created from its elements such that all elements 
 * are in increasing order.
 * 
 * Victoria has two integers m, and n. She builds unique 
 * arrays satisfying the following criteria:
 * 
 *  - Each array contains m integers.
 *  - Each integer is [1..n].
 *  - The longest increasing subsequence she can create 
 * from the array has length n.
 * 
 * Given p pairs of m and n values, print the number of arrays 
 * Victoria creates for each pair on a new line. As this number 
 * can be quite large, print your answer modulo 10^9 + 7.
 * 
 * Input Format
 * 
 * The first line contains a single positive integer, p, denoting 
 * the number of pairs.
 * Each line i of the p subsequent lines contains two space-separated 
 * integers describing the respective m and n values for a pair.
 * 
 * Output Format
 * 
 * On a new line for each pair, print a single integer denoting the 
 * number of different arrays Victoria creates modulo 10^9 + 7.
 * 
 * Sample Input
 * 
2
4 2
4 3
 *
 * Sample Output
 * 
11
9
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.worldcodesprint._5;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.LongUnaryOperator;

class LongUnaryOperatorMemoizer implements LongUnaryOperator {
    
    long[] cache;
    LongUnaryOperator oper;
    
    public LongUnaryOperatorMemoizer(LongUnaryOperator oper, int size) {
        this.oper = oper;
        this.cache = new long[size];
    }
    
    public LongUnaryOperatorMemoizer(LongUnaryOperator oper, long[] cache) {
        this.oper = oper;
        this.cache = cache;
    }
    
    @Override
    public long applyAsLong(long t) {
        int i = (int)t;
        if (cache[i] == 0)
            cache[i] = oper.applyAsLong(t);
        return cache[i];
    }
    
}

public class Longest_Increasing_Subsequence_Arrays {
    
    static final int MOD = 1000000007;
    static final long[] facMod = new long[500_000];
    static final long[] mulInvMod = new long[500_000];
    
    /*
     * Multiplicative inversion
     */
    static long mulInv(long a, long mod) {
        long x2, y2, x1, y1, x, y, r2, r1, q, r;
        x2 = 1; y2 = 0;
        x1 = 0; y1 = 1;
        for (r2 = a, r1 = mod; r1 != 0; 
                r2 = r1, r1 = r, x2 = x1, y2 = y1, x1 = x, y1 = y ) 
        {
            q = Long.divideUnsigned(r2, r1);
            r = Long.remainderUnsigned(r2, r1);
            x = x2 - (q * x1);
            y = y2 - (q * y1);
        }
        return x2 < 0? x2 + mod: x2;
    }
    
    /*
     * Unordered combinations of m things out of n
     */
    static long unorderedMod(int n, int m) {
        return ((facMod[n] * mulInvMod[m]) % MOD) * mulInvMod[n - m] % MOD;
    }
    
    static {
        facMod[0] = facMod[1] = 1;
        mulInvMod[0] = mulInv(facMod[0], MOD);
        mulInvMod[1] = mulInv(facMod[1], MOD);
        for (int i = 2; i < facMod.length; i++) {
            facMod[i] = facMod[i - 1] * i % MOD;
            mulInvMod[i] = mulInv(facMod[i], MOD);
        }
    }
    
    static long powMod(long a, long p) {
        long res = 1 % MOD, x = a % MOD;
        while (p != 0) {
            if ((p & 1) == 1) 
                res = (res * x) % MOD;
            x = (x * x) % MOD; 
            p >>>= 1;
        }
        return res;
    }
    
    static long[] cache1 = new long[500_000];
    static long[] cache2 = new long[500_000];
    static void solve(int N, int M) {
        Arrays.fill(cache1, 0);
        Arrays.fill(cache2, 0);
        LongUnaryOperatorMemoizer powBaseM = new LongUnaryOperatorMemoizer(i -> 
                powMod(M, i), cache1);
        LongUnaryOperatorMemoizer powBaseM_ = new LongUnaryOperatorMemoizer(i -> 
                powMod(M - 1, i), cache2);
        long C = 0;
        int maxNonM = N - M;
        // calculating with Y
        for (int lenY = maxNonM; lenY > 0; lenY--) {
            long numOfWaysAssignY = powBaseM.applyAsLong(lenY);
            long numOfWaysAssignX = powBaseM_.applyAsLong(maxNonM - lenY);
            long numOfArrangementsForX = unorderedMod(N - lenY - 1, maxNonM - lenY);
            C += ((numOfWaysAssignY * numOfWaysAssignX) % MOD) * numOfArrangementsForX % MOD;
            C %= MOD;
        }
        // without Y
        long numOfArrangementsForX = unorderedMod(N - 1, maxNonM);
        C += powBaseM_.applyAsLong(maxNonM) * numOfArrangementsForX % MOD;
        C %= MOD;
        System.out.println(C);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Longest_Increasing_Subsequence_Arrays.class.getResourceAsStream("Longest_Increasing_Subsequence_Arrays.in"));
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            solve(scanner.nextInt(), scanner.nextInt());
        }
        scanner.close();
    }

}
