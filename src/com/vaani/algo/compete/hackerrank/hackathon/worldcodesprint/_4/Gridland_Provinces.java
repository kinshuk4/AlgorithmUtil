/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 21/07/2016
 * 
 * Hacker rank
 * World CodeSprint #4
 * Problem: Gridland Provinces
 * Status: timeout
 * 
 * 
 * The Kingdom of Gridland contains P provinces. Each province 
 * is defined as a 2xN grid where each cell in the grid 
 * represents a city. Every cell in the grid contains a single 
 * lowercase character denoting the first character of the city 
 * name corresponding to that cell.
 * From a city with the coordinates (i, j), it is possible to 
 * move to any of the following cells in 1 unit of time (provided 
 * that the destination cell is within the confines of the grid):
 * 
 * i - 1, j
 * i + 1, j
 * i, j - 1
 * i, j + 1
 * 
 * A knight wants to visit all the cities in Gridland. He can start 
 * his journey in any city and immediately stops his journey after 
 * having visited each city at least once. Moreover, he always plans 
 * his journey in such a way that the total time required to 
 * complete it is minimum.
 * After completing his tour of each province, the knight forms a 
 * string by concatenating the characters of all the cells in his 
 * path. How many distinct strings can he form in each province?
 * 
 * Input Format
 * 
 * The first line contains a single integer, P, denoting the number 
 * of provinces. The 3 * P subsequent lines describe each province over 
 * the following three lines:
 * 
 * - The first line contains an integer, N, denoting the number of 
 * columns in the province.
 * - Each of the next two lines contains a string, S, of length N
 * denoting the characters for the first and second row of the province.
 * 
 * Output Format
 * 
 * For each province, print the number of distinct strings the knight 
 * can form on a new line.
 * 
 * Sample Input
 * 
3
1
a
a
3
dab
abd
5
ababa
babab
 *
 * Sample Output
 * 
1
8
2
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.worldcodesprint._4;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.IntFunction;

/*
 * Supports composed hash codes. Based on modular
 * hash function.
 */
class StringHash {
    
    // to decrease collisions must be prime
    private static final long M1 = 4294967291L;
    private static final long M2 = 4294967279L;
    private static long[] P1, P2;
    
    private long h1, h2;
    int len = 0;
    
    static void init(int maxSize) {
        P1 = new long[maxSize];
        P2 = new long[maxSize];
        P1[0] = 1;
        P2[0] = 1;
        for (int i = 1; i < maxSize; i++) {
            P1[i] = (P1[i - 1] * 25) % M1;
            P2[i] = (P2[i - 1] * 25) % M2;
        }
    }
    
    StringHash() {
    }
    
    StringHash(String s) {
        this(s.toCharArray());
    }
    
    StringHash(char[] a) {
        add(a, 0, a.length);
    }

    StringHash(char[] a, int s) {
        add(a, s, a.length);
    }

    StringHash(char[] a, int s, int e) {
        add(a, s, e);
    }
    
    StringHash(StringHash sh) {
        h1 = sh.h1;
        h2 = sh.h2;
        len = sh.len;
    }
    
    void clear() {
        h1 = h2 = len = 0;
    }
    
    StringHash add(StringHash sh) {
        h1 = (Long.remainderUnsigned(h1 * P1[sh.len], M1) + sh.h1) % M1;
        h2 = (Long.remainderUnsigned(h2 * P2[sh.len], M2) + sh.h2) % M2;
        len += sh.len;
        return this;
    }
    
    StringHash add(char[] a, int s, int e) {
        for (int i = s; i < e; i++) {
            int v = a[i] - 'a';
            h1 = ((h1 * 25) % M1 + v) % M1;
            h2 = ((h2 * 25) % M2 + v) % M2;
            len++;
        }
        return this;
    }
    
    long getHashCode() {
        return (h1 << 31) | h2;
    }

}

public class Gridland_Provinces {

    static int[][] DIRS = {
        {+1, 0},
        {0, +1}, // left
        {-1, 0},
        {0, +1}, // left
    };

    static Set<Long> S = new HashSet<>(1300_000);
    static int LEN = 0;
    static char[][] A;
    static char[][] IA = new char[2][];
    // hash of the string A[r] starting from i to the end of it 
    static StringHash[][] hashPostA = new StringHash[2][];
    // hash of the string A[r] starting from 0 to i
    static StringHash[][] hashPreA = new StringHash[2][];
    // hashes for IA
    static StringHash[][] hashPreIA = new StringHash[2][];
    static StringHash[][] hashPostIA = new StringHash[2][];
    
    static void reverse(char[] a) {
        for(int i = 0; i < a.length / 2; i++)
        {
            char temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }
    
    static void buildIA() {
        IA[0] = A[0].clone();
        IA[1] = A[1].clone();
        reverse(IA[0]);
        reverse(IA[1]);
    }
    
    static void buildHashes() {
        IntFunction<StringHash[]> f = i -> new StringHash[LEN];
        Arrays.setAll(hashPostA, f);
        Arrays.setAll(hashPostIA, f);
        Arrays.setAll(hashPreA, f);
        Arrays.setAll(hashPreIA, f);
        for (int r = 0; r < A.length; r++) {
            for (int j = 0; j < LEN; j++) {
                hashPostA[r][j] = new StringHash(A[r], j);
                hashPostIA[r][j] = new StringHash(IA[r], j);
                hashPreA[r][j] = new StringHash(A[r], 0, j + 1);
                hashPreIA[r][j] = new StringHash(IA[r], 0, j + 1);
            }
        }
    }
    
    static StringHash tmpHash = new StringHash();
    static void count(int r, int c, int dir, boolean isOdd, StringHash h) {
        if (c == LEN) {
            S.add(h.getHashCode());
            return;
        }
        h.add(A[r], c, c + 1);
        if (c < LEN - 1) {
            tmpHash.clear();
            tmpHash.add(h);
            tmpHash.add(hashPostA[r][c + 1]);
            tmpHash.add(hashPreIA[r ^ 1][LEN - c -  (isOdd? 2: 1)]);
            S.add(tmpHash.getHashCode());
        }        
        if (dir == DIRS.length) dir = 0;
        count(r + DIRS[dir][0], c + DIRS[dir][1], dir + 1, !isOdd, h);
    }
    
    static void count() {
        StringHash sh = new StringHash();
        for (int i = 0; i < LEN; i++) {
            for (int r = 0; r < 2; r++) {
                sh.clear();
                if (i > 0)
                    sh.add(hashPostIA[r ^ 1][LEN - i]);
                if (i > 0)
                    sh.add(hashPreA[r][i - 1]);
                count(r, i, r == 0? 0: 2, false, sh);
            }
        }
    }
    
    static int solve() {
        S.clear();
        LEN = A[0].length;
        buildIA();
        buildHashes();
        S.add(new StringHash(hashPreIA[0][LEN - 1]).add(hashPostA[1][0]).getHashCode());
        S.add(new StringHash(hashPreIA[1][LEN - 1]).add(hashPostA[0][0]).getHashCode());
        count();
        char[][] t = A; A = IA; IA = t;
        StringHash[][] tt = hashPreIA; hashPreIA = hashPreA; hashPreA = tt;
        tt = hashPostA; hashPostA = hashPostIA; hashPostIA = tt;
        count();
        return S.size();
    }
    
    static {
        StringHash.init(1200);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Gridland_Provinces.class.getResourceAsStream("Gridland_Provinces.in"));
        int p = scanner.nextInt();
        for (int i = 0; i < p; i++) {
            scanner.nextInt();
            A = new char[][]{scanner.next().toCharArray(), scanner.next().toCharArray()};
            System.out.println(solve());
        }
        scanner.close();
    }

}