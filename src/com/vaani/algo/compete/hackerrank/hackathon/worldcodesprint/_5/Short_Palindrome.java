/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 26/07/2016
 * 
 * Hacker rank
 * World CodeSprint #5
 * Problem: Short Palindrome
 * Status: accepted
 * 
 * Consider a string, s, of n lowercase English letters 
 * where each character, si (0 <= i < n), denotes the letter 
 * at index i in s. We define an (a, b, c, d) palindromic 
 * tuple of s to be a sequence of indices in s satisfying 
 * the following criteria:
 * 
 * - sa = sd, meaning the characters located at indices a and d 
 * are the same.
 * 
 * - sb = sc, meaning the characters located at indices b and c
 * are the same.
 * 
 * - 0 <= a < b < c < d < |s|, meaning that a, b, c, and d
 * are ascending in value and are valid indices within 
 * string s.
 * 
 * Given s, find and print the number of (a, b, c, d) tuples 
 * satisfying the above conditions. As this value can be quite 
 * large, print it modulo 10^9 + 7.
 * 
 * Input Format
 * 
 * A single string denoting s.
 * 
 * Output Format
 * 
 * Print the number of tuples satisfying the conditions. As this 
 * value can be quite large, print it modulo 10^9 + 7.
 * 
 * 
 * Sample Input
 * 
kkkkkkz
 *
 * Sample Output
 * 
15
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.worldcodesprint._5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Short_Palindrome {
    
    static final int LEN = 26;
    
    static int toInt(char c) {
        return c - 'a';
    }
    
    static char toChar(int i) {
        return (char)('a' + i);
    }
    
    static void reverse(char[] a) {
        for(int i = 0; i < a.length / 2; i++)
        {
            char temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }
    
    static int[] mergeSort(List<Integer> a, List<Integer> b) {
        int[] res = new int[a.size() + b.size()];
        int i1 = 0;
        int i2 = 0;
        int c = 0;
        while (i1 < a.size() && i2 < b.size()) {
            int v1 = a.get(i1);
            int v2 = b.get(i2);
            if (v1 < v2) {
                res[c++] = v1;
                i1++;
            } else {
                res[c++] = v2;
                i2++;
            }
        }
        while (i1 < a.size()) {
            res[c++] = a.get(i1++);
        }
        while (i2 < b.size()) {
            res[c++] = b.get(i2++);
        }
        return res;
    }

    static long[] countSubstrings1(char[] a, int[] idx, char[] p) {
        long[] C = new long[idx.length];
        long ca = 0;
        for (int i = 0; i < idx.length; i++) {
            C[i] = 0;
            if (a[idx[i]] == p[1])
                C[i] = ca;
            if (a[idx[i]] == p[0])
                ca++;
        }
        return C;
    }
    
    static long[] countSubstrings2(char[] a, int[] idx, char[] p) {
        long[] C = new long[idx.length];
        long c = a[idx[idx.length - 1]] == p[1]? 1: 0;
        for (int i = idx.length - 2; i >= 0; i--) {
            C[i] = C[i + 1];
            if (a[idx[i]] == p[0]) 
                C[i] = (C[i] + c) % 1000000007;
            if (a[idx[i]] == p[1])
                c++;
        }
        return C;
    }
    
    @SuppressWarnings("unchecked")
    static void solve(char[] A) {
        List<Integer>[] chars = new List[LEN];
        for (int i = 0; i < A.length; i++) {
            int ch = toInt(A[i]);
            if (chars[ch] == null)
                chars[ch] = new ArrayList<>();
            chars[ch].add(i);
        }
        boolean[] counted = new boolean[LEN];
        long C = 0;
        for (int i = 0; i < LEN; i++) {
            if (chars[i] == null) continue;
            for (int j = 0; j < LEN; j++) {
                if (chars[j] == null) continue;
                if (i == j) {
                    if (counted[i]) continue;
                    counted[i] = true;
                }
                char a = toChar(i);
                char b = toChar(j);
                int[] idx;
                if (a == b)
                    idx = chars[i].stream().mapToInt(Integer::intValue).toArray();
                else
                    idx = mergeSort(chars[i], chars[j]);
                long[] ab = countSubstrings1(A, idx, new char[]{a, b});
                long[] ba = countSubstrings2(A, idx, new char[]{b, a});
                for (int k = 0; k < idx.length - 1; k++) {
                    C += (ab[k] * ba[k + 1]) % 1000000007;
                    C %= 1000000007;
                }
            }
        }
        System.out.println(C);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Short_Palindrome.class.getResourceAsStream("Short_Palindrome.in"));
        solve(scanner.next().toCharArray());
        scanner.close();
    }

}
