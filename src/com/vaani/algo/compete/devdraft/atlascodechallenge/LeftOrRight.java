/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 03/12/2015
 *
 * Atlas Code Challenge
 * Problem: Left or right
 * Status: accepted
 * 
 * Problem
 * 
 * Given array of times between attractions where value stored by index: 
 * 1 - time needed to walk from attraction 1 to 2
 * 2 - time needed to walk from attraction 2 to 3
 * ....
 * n - time needed to walk from attraction n to 1
 * You allowed walk only left or right. To get from one attracion to 
 * another you need to walk through all attractions in between.
 * Find shortest time needed to visit queried attractions
 * 
 * Input Format
 * 
 * First line number of attractions.
 * Second line time array for them.
 * Third line number of queries.
 * Rest lines are queries lines.
 * First line in query number of attractions need to visit.
 * Second line in query attractions need to visit.
 * 
 * Output Format
 * 
 * Shortest time needed.
 * 
 * Input
 * 
6
4 2 5 1 3 8
3
2
0 1
3
0 5 3
4
0 4 2 5
 * 
 * Output
 * 
4
12
26
 * 
 */
package com.vaani.algo.compete.devdraft.atlascodechallenge;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class LeftOrRight {

    static int N;
    static int[] T, D;
    static int[] L;

    static void preproc() {
        L = new int[T.length + 1];
        int c = 0;
        L[0] = c;
        for (int i = 1; i < L.length; ++i) {
            c += T[i - 1];
            L[i] = c;
        }
    }

    static int solve(int i) {
        if (i == D.length)
            return 0;
        if (D[i - 1] < D[i])
            return solve(i + 1) + Math.min(
                    L[D[i]] - L[D[i - 1]],
                    L[D[i - 1]] + (L[L.length - 1] - L[D[i]]));
        else if (D[i - 1] > D[i])
            return solve(i + 1) + Math.min(
                    L[D[i - 1]] - L[D[i]], 
                    L[L.length - 1] - L[D[i - 1]] + L[D[i]]);
        else
            return solve(i + 1);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(LeftOrRight.class.getResourceAsStream("LeftOrRight.in"));
        N = scanner.nextInt();
        T = new int[N];
        Arrays.setAll(T, i -> scanner.nextInt());
        preproc();
        int q = scanner.nextInt();
        while (q-- > 0) {
            int d = scanner.nextInt() + 1;
            D = new int[d];
            Arrays.setAll(D, i -> i == 0? 0: scanner.nextInt());
            System.out.println(solve(1));
        }
        scanner.close();
    }

}
