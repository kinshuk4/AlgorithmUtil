/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 01/12/2015
 * 
 * Hacker rank
 * Problem: Sherlock and Queries
 * Status: accepted / timeout
 * 
 * Problem
 * 
 * Watson gives Sherlock an array A of N elements and two arrays B and C, 
 * of M elements each. Then he asks Sherlock to perform the following 
 * program:
 * 
 * for i = 1 to M do
 *     for j = 1 to N do
 *         if j % B[i] == 0 then
 *             A[j] = A[j] * C[i]
 *         endif
 *     end do
 * end do
 * 
 * This code needs to be optimized. Can you help Sherlock and tell him 
 * the resulting array A? You should print all the array elements modulo 
 * (10^9 + 7).
 * 
 * Input Format
 * 
 * The first line contains two integer, N and M. The next line contains N integers,
 * the elements of array A. The last two lines contain M integers each, the elements
 * of array B and C, respectively.
 * 
 * Output Format
 * 
 * Print N space-separated integers, the elements of array A after performing the 
 * program modulo (10^9 + 7).
 * 
 * Sample Input
 * 
4 3
1 2 3 4
1 2 3
13 29 71
 * 
 * Sample Output
 * 
13 754 2769 1508
 * 
 */

package com.vaani.algo.compete.hackerrank.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SherlockAndQueries {

    static long[] A, B, C;

    static void solve() {
        for (int i = 0; i < B.length; ++i) {
            for (int j = (int) B[i] - 1; j < A.length; j += B[i]) {
                A[j] *= C[i];
                A[j] %= 1_000_000_007;
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(new File("/home/lynx/tmp/in"));
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        A = new long[N];
        B = new long[M];
        C = new long[M];
        Arrays.setAll(A, i -> scanner.nextInt());
        Arrays.setAll(B, i -> scanner.nextInt());
        Arrays.setAll(C, i -> scanner.nextInt());
        solve();
        System.out.println(Arrays.stream(A).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        scanner.close();
    }

}
