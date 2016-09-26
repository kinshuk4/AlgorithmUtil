/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 16/04/2016
 *
 * Hacker rank
 * Problem: Angry Children 2
 * Status: accepted
 * 
 * Problem
 * 
 * Select K elements from N elements such that the sum absolute
 * value of differences of adjacent elements is minimized 
 * 
 * Input Format
 * 
 * The first line contains an integer N.
 * The second line contains an integer K.
 * N lines follow each integer
 * 
 * Output Format
 * 
 * A single integer which will be minimum adjacent difference.
 * 
 * Input
 * 
7
3
10
100
300
200
1000
20
30
 * 
 * Output
 * 
40
 * 
 */
package com.vaani.algo.compete.hackerrank.misc;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;

public class AngryChildren {

    static int K;
    static BigInteger[] N;
    
    static BiFunction<Integer, Integer, BigInteger> prod = (pos, times) ->
        N[pos].multiply(BigInteger.valueOf(times));
    
    private static BigInteger countWindow(BigInteger sum) {
        BigInteger d = BigInteger.ZERO;
        for (int i = 0; i < K; i++) {
            sum = sum.subtract(N[i]);
            d = d.add(prod.apply(i, K - i - 1).subtract(sum).abs());
        }
        return d;
    }

    private static BigInteger solve() {
        Arrays.sort(N);
        BigInteger sum = Arrays.stream(N).limit(K).reduce((b1, b2) ->
            b1.add(b2)).get();
        BigInteger d = countWindow(sum);
        BigInteger min = d;
        for (int i = K; i < N.length; ++i) {
            sum = sum.subtract(N[i - K]);
            d = d.subtract(prod.apply(i - K, K - 1).subtract(sum).abs());
            d = d.add(prod.apply(i, K - 1).subtract(sum).abs());
            sum = sum.add(N[i]);
            if (d.compareTo(min) < 0) {
                min = d;
            }
        }
        return min;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(AngryChildren.class.getResourceAsStream("AngryChildren.in"));
        int n = scanner.nextInt();
        K = scanner.nextInt();
        N = new BigInteger[n];
        Arrays.setAll(N, i -> BigInteger.valueOf(scanner.nextLong()));
        System.out.println(solve());
        scanner.close();
    }

}
