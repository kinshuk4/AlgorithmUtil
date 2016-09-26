/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 25/12/2015
 * 
 * Problem
 * 
 * Given matrix A size of N × N, filled with non-negative numbers. 
 * Distance between two elements Aij and Apq equal to |i - p| + |j - q|.
 * Need to change each zero number of the matrix with its nearest non-zero neighbor.
 * If there is two and more candidates zero should be preserved.
 * 
 * Input Format
 * 
 * First line contains number N (1 ≤ N ≤ 200, 0 ≤ Aij ≤ 1000000). 
 * Then comes N lines with N numbers, separated with whitespaces.
 * 
 * Output Format
 * 
 * Updated matrix
 * 
 * Sample Input
 * 
3
0 0 0
1 0 2
0 3 0
 *
 * Sample Output
 * 
1 0 2
1 0 2
0 3 0
 *
 *
 */

package com.vaani.algo.compete.mensikovbook;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class NearestNumber {

    static int N;
    static int[][] A;

    static class pair {
        int x, y, d;
        boolean dirty;
        pair(int x, int y, int d) {
            this.x = x; this.y = y; this.d = d;
        }
        pair(pair p) {
            this.x = p.x; this.y = p.y; this.d = p.d; this.dirty = p.dirty;
        }
    }

    static void collide(pair a, pair b) {
        if (a.d != b.d)
            return;
        boolean f = true;
        if (a.x == b.x && a.y == b.y) {
            f = a.dirty | b.dirty;
        }
        a.dirty = f;
        b.dirty = f;
    }
    
    private static pair min(pair l, pair r) {
        return l.d < r.d? l: r;
    }
    
    private static void solve() {
        pair[][] a = new pair[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                a[i][j] = new pair(i, j, A[i][j] != 0? 0: Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (a[i][j].d == 0)
                    continue;
                pair l = i > 0? new pair(a[i - 1][j]): new pair(i, j, Integer.MAX_VALUE);
                pair r = j > 0? new pair(a[i][j - 1]): new pair(i, j, Integer.MAX_VALUE);
                if (l.d != Integer.MAX_VALUE)
                    l.d++;
                if (r.d != Integer.MAX_VALUE)
                    r.d++;
                collide(l, r);
                a[i][j] = min(l, r);                
            }
        }
        for (int i = N - 1; i >= 0 ; --i) {
            for (int j = N - 1; j >= 0 ; --j) {
                if (a[i][j].d == 0)
                    continue;
                pair l = i < N - 1? new pair(a[i + 1][j]): new pair(i, j, Integer.MAX_VALUE);
                pair r = j < N - 1? new pair(a[i][j + 1]): new pair(i, j, Integer.MAX_VALUE);
                if (l.d != Integer.MAX_VALUE)
                    l.d++;
                if (r.d != Integer.MAX_VALUE)
                    r.d++;
                collide(l, r);
                l = min(l, r);
                collide(l, a[i][j]);
                a[i][j] = min(a[i][j], l);
            }
        }
        /*for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                System.out.print(a[i][j].d + " ");
            }
            System.out.println();
        }
        System.out.println(" ");
        */
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (A[i][j] != 0 || a[i][j].dirty) {
                    System.out.print(A[i][j] + " ");
                    continue;
                }
                System.out.print(A[a[i][j].x][a[i][j].y] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(
                        NearestNumber.class.getResourceAsStream("NearestNumber.in"));
        N = scanner.nextInt();
        A = new int[N][N];
        for (int i = 0; i < N; ++i) 
        {
            Arrays.setAll(A[i], j -> scanner.nextInt());
        }
        solve();
        scanner.close();
    }

}
