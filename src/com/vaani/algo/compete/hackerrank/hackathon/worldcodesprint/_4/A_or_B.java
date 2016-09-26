/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 21/06/2016
 * 
 * Hacker rank
 * World CodeSprint #4
 * Problem: A or B
 * Status: accepted
 * 
 * Consider four numbers: A, B, C, and K. You must change at 
 * most K bits in A and B to form the numbers A' and B' satisfying 
 * the equation A' | B' = C. Here, the | symbol denotes the bitwise 
 * OR operation.
 * Given Q sets of the numbers defined above, find and print the 
 * respective values of A' and B' on new lines;
 * - if no such value exists, print -1 instead. If there are multiple 
 * solutions, make A' as small as possible;
 * - if there are still multiple solutions, make B' as small as possible. 
 * A, B, and C are given in Hexadecimal (base 16), and K is given in 
 * decimal (base 10).
 * 
 * If the number of bits changed in A is ka and the number of bits changed 
 * in B is kb, then ka + kb must be <= K.
 * 
 * Input Format
 * 
 * The first line contains an integer, Q, denoting the number of queries.
 * The subsequent lines describe each respective query as follows:
 * - The first line contains a single integer denoting the value of K.
 * - Each of the next 3 lines contains a Hexadecimal (base 16) number 
 * describing the respective values of A, B, and C.
 * 
 * Output Format
 * 
 * Print two lines of output for each query:
 * - The first line should contain a Hexadecimal (base 16) number 
 * denoting the value of A'.
 * - The second line must contain a Hexadecimal (base 16) number 
 * denoting the value of B'.
 * If no valid answer exists, you must instead print one line of output 
 * with the integer -1.
 * 
 * Sample Input
 * 
3
8
2B
9F
58
5
B9
40
5A
2
91
BE
A8
 *
 * Sample Output
 * 
8
58
18
42
-1
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.worldcodesprint._4;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class A_or_B {

    static int[][][] MK = new int[16][16][16];
    static int[][][] MA = new int[16][16][16];
    static int[][][] MB = new int[16][16][16];
    
    static {
        for (int j = 0; j < 16; j++) {
            for (int jj = 0; jj < 16; jj++) {
                for (int jjj = 0; jjj < 16; jjj++) {
                    MK[j][jj][jjj] = -1;
                    MA[j][jj][jjj] = -1;
                    MB[j][jj][jjj] = -1;
                }
            }
        }
    }
    
    static int[] H = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    
    static char hexDigit(int num) {
        return num > 9? (char)('A' + (num - 10)): (char)('0' + num);
    }
    
    static int intDigit(char hexNum) {
        return H[hexNum >= '0' && hexNum <= '9'? hexNum - '0': 10 + (hexNum - 'A')];
    }
    
    static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
    
    static String trimLeadingZeros(String s) {
        while (s.charAt(0) == '0') s = s.substring(1);
        return s;
    }
    
    static String trimLeadingZeros(char[] a) {
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != '0') break;
            c++;
        }
        return c == a.length? "0": new String(a, c, a.length - c);
    }
    
    static char[] addZeros(char[] a, int max) {
        if (a.length == max) return a;
        char[] b = new char[max];
        int s = max - a.length;
        for (int i = 0; i < b.length; i++) {
            if (i < s) b[i] = '0';
            b[i] = a[i - s];
        }
        return b;
    }
    
    /*
     * Counts how many bit changes needs to be done in a and b
     * to obtain c. New a and b stored in r.
     */
    static int count(int a, int b, int c, int i, int[] r) {
        if (a == b && b == c && c == 0) return 0;
        if ((c & 1) == 1) {
            if ((a & 1) == 1 || (b & 1) == 1) {
                r[0] |= (a & 1) << i;
                r[1] |= (b & 1) << i;
                return count(a >> 1, b >> 1, c >> 1, i + 1, r);
            }
            r[1] |= 1 << i;
            return 1 + count(a >> 1, b >> 1, c >> 1, i + 1, r);            
        } else {
            int k = 0;
            if ((a & 1) == 1) k++;
            if ((b & 1) == 1) k++;
            return k + count(a >> 1, b >> 1, c >> 1, i + 1, r);
        }
    }
    
    static void solve(int K, char[] a, char[] b, char[] c) {
        int i = 0;
        int k = 0;
        int max = Math.max(a.length, Math.max(b.length, c.length));
        a = addZeros(a, max);
        b = addZeros(b, max);
        c = addZeros(c, max);
        int[][] res = {new int[max], new int[max]};
        while (i < a.length) {
            int ah = intDigit(a[i]);
            int bh = intDigit(b[i]);
            int ch = intDigit(c[i]);
            int[] r = new int[2];
            if (MK[ah][bh][ch] != -1) {
                r[0] += MA[ah][bh][ch];
                r[1] += MB[ah][bh][ch];
            } else {
                MK[ah][bh][ch] = count(ah, bh, ch, 0, r);
                MA[ah][bh][ch] = r[0];
                MB[ah][bh][ch] = r[1];
            }
            k += MK[ah][bh][ch];
            if (k > K) {
                System.out.println("-1");
                return;
            }
            res[0][i] = r[0];
            res[1][i] = r[1];
            i++;
        }
        int[] mask = {0x8, 0x4, 0x2, 0x1};
        for (int j = 0; j < res[0].length && k < K; j++) {
            for (int m: mask) {
                if (k >= K) break;
                if ((res[0][j] & m) == 0) continue;
                if ((res[1][j] & m) == m) {
                    res[0][j] &= ~m;
                    k++;
                } else if (k + 2 <= K) {
                    res[0][j] &= ~m;
                    res[1][j] |= m;
                    k += 2;
                }
            }
        }
        for (int j = 0; j < res[0].length; j++) {
            a[j] = hexDigit(res[0][j]);
            b[j] = hexDigit(res[1][j]);
        }
        System.out.println(trimLeadingZeros(a));
        System.out.println(trimLeadingZeros(b));
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(A_or_B.class.getResourceAsStream("A_or_B.in"));
        IntStream.range(0, scanner.nextInt())
            .forEach(i -> solve(scanner.nextInt(), scanner.next().toCharArray(), scanner.next().toCharArray(), scanner.next().toCharArray()));
        scanner.close();
    }

}
