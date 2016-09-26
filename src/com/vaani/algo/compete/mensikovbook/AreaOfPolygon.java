/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 12/03/2016
 * 
 * Problem
 * 
 * Write a function which calculates area of a polygon.
 * 
 * Input
 *
4
-2 2
-2 -2
2 -2
2 2
 * 
 * Output
 * 
16.0
 * 
 */

package com.vaani.algo.compete.mensikovbook;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class AreaOfPolygon {

    static int[] X, Y;
    static int N;
    
    static double calcInTrapezoid(int a, int b)
    {
        return (Y[a] + Y[b]) * (X[b] - X[a]) / 2.0;
    }
    
    static double solve()
    {
        double[] sum = new double[1];
        IntStream.range(1, N).forEach(i -> sum[0] += calcInTrapezoid(i - 1, i));
        sum[0] += calcInTrapezoid(N - 1, 0);
        return Math.abs(Math.round(sum[0] * 10) / 10.0);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null?
                new Scanner(System.in): new Scanner(AreaOfPolygon.class.getResourceAsStream("AreaOfPolygon.in"));
        N = scanner.nextInt();
        X = new int[N];
        Y = new int[N];
        int[] min = new int[2];
        for (int i = 0; i < N; ++i) {
            X[i] = scanner.nextInt();
            Y[i] = scanner.nextInt();
            min[0] = Math.min(min[0], X[i]);
            min[1] = Math.min(min[1], Y[i]);
        };
        if (min[0] < 0)
            Arrays.setAll(X, i -> X[i] - min[0]);
        if (min[1] < 0)
            Arrays.setAll(Y, i -> Y[i] - min[1]);
        System.out.println(solve());
        scanner.close();
    }
    
}
