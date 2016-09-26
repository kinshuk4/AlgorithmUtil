/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 01/05/2016
 *
 * Amazon challange
 * Problem: Lockers grid
 * Status: accepted
 * 
 * Problem
 * 
 * Implement function:
 * 
 * int[][] getLockerDistanceGrid(int w, int l, int[] y, int[] x)
 *
 * Where w, l width and length of the grid and y and x coordinates of the lockers.
 * Function should return grid where each cell contains a shortest distance to the locker.
 * 
 */
package com.vaani.algo.compete.hackerrank.amazon;

import java.util.Arrays;

public class Test1 {
    
    static int[][] A;
    static int G = 0, X = Integer.MAX_VALUE;
    
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }
    
    static interface Operator {
        int apply(int x, int y);
    }
    
    static void update(int x, int y, Operator oper) {
        if (A[y][x] != G)
            A[y][x] = oper.apply(x, y);
    }
    
    static void bfs(Point p, int radius, Operator oper) {
        for (int x = p.x - radius; x <= p.x + radius; ++x) {
            if (x < 0 || x >= A[0].length) continue;
            int y = p.y - radius;
            if (y >= 0 && y < A.length) {
                update(x, y, oper);
            }
            y = p.y + radius;
            if (y >= 0 && y < A.length) {
                update(x, y, oper);
            }
        }
        for (int y = p.y - radius; y <= p.y + radius; ++y) {
            if (y < 0 || y >= A.length) continue;
            int x = p.x - radius;
            if (x >= 0 && x < A[y].length) {
                update(x, y, oper);
            }
            x = p.x + radius;
            if (x >= 0 && x < A[y].length) {
                update(x, y, oper);
            }
        }
    }
    
    /*
     * Non java 8 implementation version
     */
    static void bfs(final Point p)
    {
        Operator fn = new Operator() {
            @Override
            public int apply(int x, int y) {
                A[y][x] = Math.min(A[y][x], Math.abs(p.x - x) + Math.abs(p.y - y));
                return A[y][x];
            }
        };
        for (int r = 1; r < Math.max(A.length, A[0].length); ++r)
            bfs(p, r, fn);
    }
    
    static void solve() {
        for (int y = 0; y < A.length; y++)
        {
            for (int x = 0; x < A[0].length; x++)
            {
                if (A[y][x] == G)
                    bfs(new Point(x, y));
            }
        }
        for (int y = 0; y < A.length; y++) {
            String l = Arrays.toString(A[y]);
            l = l.substring(1, l.length() - 1).replace(", ", "");
            System.out.println(l);
        }
    }

    static int[][] getLockerDistanceGrid(int w, int l, int[] y, int[] x) {
        A = new int[w][l];
        for (int i = 0; i < A.length; i++) {
            Arrays.fill(A[i], X);
        }
        for (int i = 0; i < x.length; i++) {
            A[y[i] - 1][x[i] - 1] = G;
        }
        solve();
        return A;
    }

}
