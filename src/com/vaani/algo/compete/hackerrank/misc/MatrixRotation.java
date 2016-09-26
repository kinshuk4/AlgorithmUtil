/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 24/09/2015
 * 
 * Hacker rank
 * Problem: [Algo] Matrix Rotation
 * Status: accepted
 * 
 * You are given a 2D matrix, a, of dimension MxN and a positive integer R. 
 * You have to rotate the matrix R times and print the resultant matrix. 
 * Rotation should be in anti-clockwise direction.
 * Rotation of a 4x5 matrix is represented by the following figure. Note that
 * in one rotation, you have to shift elements by one step only (refer sample 
 * tests for more clarity).
 * It is guaranteed that the minimum of M and N will be even. 
 * 
 * Input Format
 * 
 * First line contains three space separated integers, M, N and R, where M is 
 * the number of rows, N is number of columns in matrix, and R is the number 
 * of times the matrix has to be rotated.
 * Then M lines follow, where each line contains N space separated positive 
 * integers. These M lines represent the matrix. 
 * 
 * Output Format
 * 
 * Print the rotated matrix.
 * 
 * Sample Input
 * 
4 4 1
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
 * 
 * Sample Output
 * 
2 3 4 8
1 7 11 12
5 6 10 16
9 13 14 15
 * 
 */

package com.vaani.algo.compete.hackerrank.misc;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixRotation {

    static Point next(int f, int h, int w, int d, int i) {
        int s = 2 * (h + w - 2);
        i += d;
        if (i < h)
            return new Point(f + i, f);
        if (i < s / 2 + 1)
            return new Point(f + h - 1, f + i - h + 1);
        if (i < s - w + 2)
            return new Point(f + s - i - w + 1, f + w - 1);
        if (i < s + 1)
            return new Point(f, f + s - i);
        return next(f, h, w, 0, i - s);
    }

    static long[][] A;
    static long[][] B;
    static int H, W;
    static long R;
    
    static void rotate(int f) {
        int h = H - 2 * f;
        int w = W - 2 * f;
        int len = 2 * (h + w - 2);
        int r = (int)(R % len);
        for (int i = 0; i < len; ++i) {
            Point from = next(f, h, w, 0, i);
            Point to = next(f, h, w, r, i);
            B[to.x][to.y] = A[from.x][from.y];
//            for (int m = 0; m < H; ++m)
//                System.out.println(Arrays.toString(B[m]));
//            System.out.println();

        }
    }

    static void rotate() {
        int f = Math.min(W, H) / 2 - 1;
        while (f >= 0) {
            rotate(f);
            f--;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null?
                new Scanner(System.in): new Scanner(MatrixRotation.class.getResourceAsStream("MatrixRotation.in"));
        H = scanner.nextInt();
        W = scanner.nextInt();
        R = scanner.nextLong();
        A = new long[H][W];
        for (int i = 0; i < H; ++i)
            Arrays.setAll(A[i], j -> scanner.nextLong());
        B = new long[H][];
        Arrays.setAll(B, i -> Arrays.copyOf(A[i], A[i].length));
        rotate();
        for (int i = 0; i < H; ++i)
            System.out.println(Arrays.toString(B[i]).replaceAll("\\[|\\]|,", ""));
        scanner.close();        
    }
    
}
