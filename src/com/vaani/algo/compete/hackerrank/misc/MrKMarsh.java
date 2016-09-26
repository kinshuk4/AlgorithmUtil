/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 26/05/2016
 *
 * Hacker rank
 * Problem: Matrix
 * Status: accepted
 * 
 * Problem
 * 
 * Mr K has a rectangular land of size . There are marshes in the 
 * land where the fence cannot hold. Mr K wants you to find the perimeter
 * of the largest rectangular fence that can be built on this land.
 * 
 * Input Format
 * 
 * The first line contains and . The next lines contain characters each
 * describing the state of the land. 'x' (ascii value: 120) if it is a 
 * marsh and '.' ( ascii value:46) otherwise.
 * 
 * Output Format
 * 
 * Output contains a single integer - the largest perimeter. If the rectangular
 * fence cannot be built, print "impossible" (without quotes).
 * 
 * Input
 * 
4 5
.....
.x.x.
.....
.....
 * 
 * Output
 * 
14
 * 
 */
package com.vaani.algo.compete.hackerrank.misc;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.stream.IntStream.range;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MrKMarsh {

    static char[][] M;
    
    static int perim(int h, int w) {
        return w * 2 + (h - 2) * 2;
    }
    
    static void solve() {
        int[][] V = new int[M.length][M[0].length];
        int[][] H = new int[M.length][M[0].length];
        int max = 0;
        for (int r = 0; r < M.length; r++) {
            for (int c = 0; c < M[r].length; c++) {
                if (M[r][c] != '.') continue;
                H[r][c] = 1;
                if (c != 0) {
                    H[r][c] += H[r][c - 1]; 
                }
                V[r][c] = 1;
                if (r != 0) {
                    V[r][c] += V[r - 1][c]; 
                }
                int h = V[r][c];
                while (h > 1) {
                    int w = min(H[r][c], H[r - h + 1][c]);
                    if (max >= perim(V[r][c], H[r][c]))
                        break;
                    while (w > 1) {
                        if (V[r][c - w + 1] >= h)
                            max = max(max, perim(h, w));
                        w--;
                    }
                    h--;
                }
//                System.out.format("%d %d %d %d %d\n", r, c, H[r][c], V[r][c], max);
            }
        }
        System.out.println(max == 0? "impossible": max);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(MrKMarsh.class.getResourceAsStream("MrKMarsh.in"));
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        M = new char[r][c];
        scanner.nextLine();
        range(0, r).forEach(i -> M[i] = scanner.nextLine().toCharArray());
        solve();
        scanner.close();
    }

}
