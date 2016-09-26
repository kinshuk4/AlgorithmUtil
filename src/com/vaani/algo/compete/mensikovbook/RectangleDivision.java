/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 19/06/2016
 * 
 * Problem
 * 
 * Given set of rectangles in format x1 y1 x2 y2, calculate
 * on how many parts they split the plane.
 * 
 * Input
 *
3
10 20 50 30
40 10 50 25
40 25 80 30
 * 
 * Output
 * 
6
 * 
 */

package com.vaani.algo.compete.mensikovbook;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class RectangleDivision {

    static int[] RX, RY;
    static int[][] M;

    static final int FILL = 1;
    
    static int x(int x) {
        return x * 2 + 1;
    }

    static int y(int y) {
        return y * 2 + 1;
    }

    static void floodFill(int y, int x) {
        M[y][x] = FILL;
        if (x + 1 < M[y].length && M[y][x + 1] == 0) {
            floodFill(y, x + 1);
        }
        if (x - 1 >= 0 && M[y][x - 1] == 0) {
            floodFill(y, x - 1);
        }
        if (y + 1 < M.length && M[y + 1][x] == 0) {
            floodFill(y + 1, x);
        }
        if (y - 1 >= 0 && M[y - 1][x] == 0) {
            floodFill(y - 1, x);
        }
    }
    
    static void printMatrix(int[][] a) {
        IntStream.range(0, a.length).forEach((i) -> System.out.println(Arrays.toString(a[i])));
    }
    
    static int solve() {
        int[] X = Arrays.stream(RX).distinct().sorted().toArray();
        int[] Y = Arrays.stream(RY).distinct().sorted().toArray();
        M = new int[y(Y.length - 1) + 2][x(X.length - 1) + 2];
        for (int i = 0; i < RX.length; i += 2) {
            int x1 = x(Arrays.binarySearch(X, RX[i]));
            int x2 = x(Arrays.binarySearch(X, RX[i + 1]));
            int y1 = y(Arrays.binarySearch(Y, RY[i]));
            int y2 = y(Arrays.binarySearch(Y, RY[i + 1]));
            int from = Math.min(x1, x2 + 1);
            int to = Math.max(x1, x2 + 1);
            Arrays.fill(M[y1], from, to, FILL);
            Arrays.fill(M[y2], from, to, FILL);
            from = Math.min(y1, y2);
            to = Math.max(y1, y2);
            for (int y = from; y <= to; ++y) {
                M[y][x1] = FILL;
                M[y][x2] = FILL;
            }
        }
//        printMatrix(M);
        int c = 0;
        for (int y = 0; y < M.length; ++y) {
            for (int x = 0; x < M[y].length; ++x) {
                if (M[y][x] == 0) {
                    floodFill(y, x);
                    ++c;
                }
            }
        }
        return c;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null?
                new Scanner(System.in): new Scanner(RectangleDivision.class.getResourceAsStream("RectangleDivision.in"));
        int n = scanner.nextInt();
        RX = new int[2 * n];
        RY = new int[2 * n];
        for (int i = 0; i < n * 2; i += 2) {
            RX[i] = scanner.nextInt();
            RY[i] = scanner.nextInt();
            RX[i + 1] = scanner.nextInt();
            RY[i + 1] = scanner.nextInt();
        };
        System.out.println(solve());
        scanner.close();
    }
    
}
