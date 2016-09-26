/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 12/06/2016
 *
 * Hacker rank
 * BlackRock CodeSprint
 * Problem: Perfect Separating
 * Status: timeout
 * 
 * Problem
 * 
 * In this problem, when we say "sequence", we only consider sequences 
 * that are subsequences of (1, 2, 3, .., n).
 * The complement of a sequence x = (x1 < x2 < .. < xk) is defined as 
 * the sequence of values in (1, 2, 3, .., n) that are not in x. 
 * In other words, the complement of x is the unique sequence 
 * y = (y1 < y2 < .. < yn-k) with the following properties:
 * 
 *   {y1, .., yn-k} U {x1, .., xk} = {1, .., n}
 *   {y1, .., yn-k} ∩ {x1, .., xk} = 0
 * 
 * Consider a string, s, of length n composed of the letters a and/or b.
 * We call a sequence x = (x1 < x2 < .. < xk) perfect with respect to
 * s if sx1..sxk = sy1..syn-k where where y = (y1 < y2 < .. < yn-k) is 
 * the complement of x. 
 * Given s, calculate the number of perfect sequences with respect to s.
 * 
 * Input Format
 * 
 * A single string denoting s.
 * 
 * Output Format
 * 
 * Print a single integer denoting the number of perfect sequences
 * with respect to s.
 * 
 * Input
 * 
aaa
aaaa
 * 
 * Output
 * 
0
6
 * 
 */
package com.vaani.algo.compete.hackerrank.codesprint.blackrock;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Perfect_Separating {
    
    static long M[] = new long[50];
    static char[] A;
    static char[] L;
    static char[] R;
    static int LLEN;
    static int RLEN;
    
    static long count(int c, int p) {
        if (c == 0) {
            int i = RLEN;
            while (p < A.length) if (L[i++] != A[p++]) return 0;
            return 1;
        }
        if (LLEN == RLEN && M[p] != -1) {
            return M[p];
        }
        long ret = 0;
        boolean canSkip = RLEN == LLEN;
        L[LLEN] = A[p];
        LLEN++;
        ret = count(c - 1, p + 1) * (canSkip? 2: 1);
        LLEN--;
        if (!canSkip && c < A.length - p && (LLEN < RLEN || L[RLEN] == A[p])) {
            R[RLEN] = A[p];
            RLEN++;
            ret += count(c, p + 1);
            RLEN--;
        }
        if (LLEN == RLEN) M[p] = ret;
        return ret;
    }

    static long solve() {
        if ((A.length & 1) == 1) return 0;
        int c = 0;
        for (char ch: A) if (ch == 'a') c++;
        if ((c & 1) == 1) return 0;
        if (((A.length - c) & 1) == 1) return 0;
        Arrays.fill(M, -1);
        L = new char[A.length / 2];
        R = new char[L.length];
        return count(L.length, 0);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.getProperty("local") == null? 
                System.in: Perfect_Separating.class.getResourceAsStream("Perfect_Separating.in"));
        A = scanner.nextLine().toCharArray();
        System.out.println(solve());
        scanner.close();
    }

}