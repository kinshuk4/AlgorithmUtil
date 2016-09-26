/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 15/12/2015
 * 
 * Problem
 * 
 * Fibo string N defined as: F(1) = 'a', F(2) = 'b', F(n) = F(n - 1) + F(n - 2)
 * where + is concatenation. Need to count how many times substring S occurs in Fibo string N.
 * 
 * Input
 *
8
bbabab
 * 
 * Output
 * 
3
 * 
 */
package com.vaani.algo.compete.mensikovbook;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class FiboStrings {

    static int N;
    static String S;
    
    static int fiboString(String[] a) {
        a[0] = "a";
        a[1] = "b";
        int i = 2;
        while (a[i - 1].length() < S.length()) {
            a[i] = a[i - 1] + a[i - 2];
            i++;
        }
        a[i] = a[i - 1] + a[i - 2];
        return i;
    }
    
    static int count(String in) {
        int p = in.indexOf(S);
        int c = 0;
        while (p != -1) {
            c++;
            p = in.indexOf(S, p + 1);
        }
        return c;
    }
    
    private static int solve() {
        final int MAX_N = 45;
        String[] post = new String[MAX_N];
        int k = fiboString(post);
        if (N <= k)
            return count(post[N - 1]);
        int[] c = new int[MAX_N];
        c[k - 1] = count(post[k - 1]);
        c[k] = count(post[k]);
        String pre = post[k - 1];
        for (int i = k + 1; i < N; ++i) {
            String s = post[i - 1];
            s = s.substring(s.length() - S.length() + 1);
            s += pre.substring(0, S.length() - 1);
            c[i] = count(s) + c[i - 1] + c[i - 2];
            post[i] = post[i - 2];
        }
        return c[N - 1];
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(
                        FiboStrings.class.getResourceAsStream("FiboStrings.in"));
        N = scanner.nextInt();
        S = scanner.next().toLowerCase();
        System.out.println(solve());
        scanner.close();
    }

}
