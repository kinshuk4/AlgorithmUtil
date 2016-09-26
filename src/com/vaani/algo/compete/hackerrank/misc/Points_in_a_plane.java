/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 31/05/2016
 *
 * Hacker rank
 * Problem: Points in a Plane
 * Status: accepted
 * 
 * Problem
 * 
 * There are N points on an XY plane. In one turn, you can select a 
 * set of collinear points on the plane and remove them. Your goal 
 * is to remove all the points in the least number of turns. Given 
 * the coordinates of the points, calculate two things:
 * - The minimum number of turns (T) needed to remove all the points.
 * - The number of ways to to remove them in T turns. Two ways are 
 * considered different if any point is removed in a different turn.
 * 
 * Input Format
 * 
 * The first line contains the number of test cases T. T test cases 
 * follow. Each test case contains N on the first line, followed by 
 * N lines giving the coordinates of the points.
 * 
 * Output Format
 * 
 * Output T lines, one for each test case, containing the least number 
 * of turns needed to remove all points and the number of ways to do so. 
 * As the answers can be large, output them modulo 1000000007.
 * 
 * Input
 * 
2  
3  
0 0  
0 1  
1 0  
4  
3 4  
3 5  
3 6  
5 5
 * 
 * Output
 * 
2 6  
2 8
 * 
 */
package com.vaani.algo.compete.hackerrank.misc;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.LongStream;

public class Points_in_a_plane {

    static int[] X, Y;
    static int[] M = new int[2<<16];
    static int[] C = new int[2<<16];
    
    static boolean isCollinear(int src, int p1, int p2) 
    {
        return (X[p1] - X[src]) * (Y[p2] - Y[src]) -
                (Y[p1] - Y[src]) * (X[p2] - X[src]) == 0?
                        true: false;
    }
    
    static long fac(int n) {
        if (n <= 1) return 1;
        //System.out.println(n);
        return LongStream.range(1, n + 1)
                .reduce((r, i) -> r * i % 1000000007)
                .getAsLong();
    }

    static int next(int n, int s) {
        for (int i = s; i < X.length; ++i)
            if ((n & (1 << i)) == 0) return i;
        return -1;
    }
    
    static void updateMC(int n, int ni) {
        if (M[n] > M[ni] + 1) {
            M[n] = M[ni] + 1;
            C[n] = 0;
        }
        if (M[n] == M[ni] + 1)
            C[n] += C[ni];
    }
    
    static void count(int n) {
        //System.out.println(Integer.toBinaryString(n));
        if (M[n] != Integer.MAX_VALUE) return;
        int i = next(n, 0);
        int ni = n;
        ni = ni | 1 << i;
        count(ni);
        updateMC(n, ni);
        int j = i + 1;
        while ((j = next(ni, j)) != -1) {
            List<Integer> col = new ArrayList<>();
            int k = j + 1;
            int nij = ni | 1 << j;
            while ((k = next(nij, k)) != -1) {
                if (isCollinear(i, j, k)) col.add(1 << k);
                k++;
            }
            int mask = ~(-1 << col.size());
            while (mask >= 0) {
                int nn = nij;
                for (int v = 0; v < col.size(); ++v)
                    if ((mask & 1 << v) != 0) {
                        nn = nn | col.get(v);
                    }
                count(nn);
                updateMC(n, nn);
                mask--;
            }
            j++;
        }
    }
    
    static void solve() {
        Arrays.fill(M, Integer.MAX_VALUE);
        Arrays.fill(C, 0);
        int all = ~(-1 << X.length);
        M[all] = 0;
        C[all] = 1;
        count(0);
        System.out.format("%d %d\n", M[0], fac(M[0]) * C[0] % 1000000007);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.getProperty("local") == null? 
                System.in: Points_in_a_plane.class.getResourceAsStream("Points_in_a_plane.in"));
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            X = new int[n];
            Y = new int[n];
            for (int i = 0; i < n; i++) {
                X[i] = scanner.nextInt();
                Y[i] = scanner.nextInt();
            };
            solve();
        }
        scanner.close();
    }

}
