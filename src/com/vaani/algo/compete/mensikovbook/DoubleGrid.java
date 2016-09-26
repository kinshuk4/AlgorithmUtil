/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 15/03/2016
 * 
 * Problem
 * 
 * There are two grids one with cell sizes x1 x y1 and second with x2 x y2.
 * Both grids are infinite in all directions.
 * Initially one grid was put above another so that they center points was
 * matched. Because grids have cells of different dimensions in the configuration
 * like that they formed many cells of new dimensions.
 * Then center point of the first grid has shifted with delta Dx, Dy.
 * Need to find all unique areas of the cells which they produce.
 * 
 * Input format x1, y1, x2, y2, Dx, Dy.
 * 
 * 1 ≤ x1, y1, x2, y2 ≤ 100 
 * 0 ≤ Dx < x1
 * 0 ≤ Dy < y1
 * 
 * Input
 *
20 20 12 10 2 0
 * 
 * Output
 * 
4
20
60
100
120
 * 
 */
package com.vaani.algo.compete.mensikovbook;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DoubleGrid {

    static int W1, W2, H1, H2, Dx, Dy;
    static boolean[] Ax;
    static boolean[] Bx;
    static boolean[] Ay;
    static boolean[] By;
    
    static void shift(boolean[] a, int s, int d) {
        if (d == 0 || s == d) return;
        for (int i = 0; i < a.length; i += s) {
            if (i + d < a.length) a[i + d] = true;
            a[i] = false;
        }
    }
    
    static void createAxis(boolean[] a, int s, int d) {
        a[0] = true;
        for (int i = 1; i < a.length; ++i) {
            a[i] = i >= s && i % s == 0;
        }
        shift(a, s, d);
        //IntStream.range(0, a.length).filter(i -> a[i]).forEach(System.out::println);
    }
    
    static int[] solve() {
        Ax = new boolean[W1 * W2];
        Bx = new boolean[W1 * W2];
        Ay = new boolean[H1 * H2];
        By = new boolean[H1 * H2];

        createAxis(Ax, W1, Dx);
        createAxis(Ay, H1, Dy);
        createAxis(Bx, W2, 0);
        createAxis(By, H2, 0);
        Set<Integer> s = new HashSet<>();
        int sy = 0;
        for (int y = 0; y < Ay.length; ++y) {
            if (!Ay[y] && !By[y]) continue;
            int sx = 0;
            for (int x = 0; x < Ax.length; ++x) {
                if (!Ax[x] && !Bx[x]) continue;
                s.add((y - sy) * (x - sx));
                sx = x;
            }
            sy = y;
        }
        s.remove(0);
        return s.stream().mapToInt(Integer::intValue).sorted().toArray();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null?
                new Scanner(System.in): new Scanner(DoubleGrid .class.getResourceAsStream("DoubleGrid.in"));
        W1 = scanner.nextInt();
        H1 = scanner.nextInt();
        W2 = scanner.nextInt();
        H2 = scanner.nextInt();
        Dx = scanner.nextInt();
        Dy = scanner.nextInt();
        int[] r = solve();
        System.out.println(r.length);
        Arrays.stream(r).forEach(System.out::println);
        scanner.close();
    }

}
