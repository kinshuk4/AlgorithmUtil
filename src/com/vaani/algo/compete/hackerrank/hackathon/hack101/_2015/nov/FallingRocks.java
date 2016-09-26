/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 24/12/2015
 * 
 * Hacker rank
 * 101 Hack Nov 2015
 * Problem: Falling Rocks
 * Status: accepted
 * 
 * You suddenly fall into a magical, 2D world laid out on a W ∗ H grid. 
 * Looking around, you realize you are somewhere in the bottom row at a
 * general coordinate of (∗,1). There are several rocks in other cells.
 * 
 * You notice the rocks are falling down at a constant rate of one unit
 * of distance per increment of time. The rock at each (x,y) at time t 
 * will be at (x,y−1) at time t+1. A rock will disappear from the world
 * once y becomes 0.
 * 
 * You must choose to remain in place, move left, or move right to avoid
 * being squashed by the falling rocks. Like the rocks, you can only move
 * one unit of distance per increment of time. If your location at time t
 * is (x,1), then you may stay at (x, 1), move to (x − 1, 1), 
 * or move to (x + 1, 1). The destination cell must exist within the range
 * of W and must not be occupied by a rock at both times, t and t+1.
 * 
 * Note: This is a magical world where time is discrete.
 * 
 * Input Format
 * 
 * The first line is contains two space-separated integers, W and H.
 * 
 * The following H lines of input each contain W characters that describe 
 * a row in the grid. The jth character of the ith line defines the status 
 * of the cell at coordinates (i, j). The descriptive characters are as follows:
 * 
 *     R indicates that location contains a rock.
 *     Y indicates you are occupying that location.
 *     E indicates an empty cell.
 * 
 * Output Format
 * 
Print YES if you can avoid hitting any rocks. Otherwise, print NO.
 * 
 * Sample Input
 * 
5 5
REYEE
EEREE
EREEE
EEERR
REREE
 *
 * Sample Output
 * 
YES
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.hack101._2015.nov;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class FallingRocks {

    static int W, H;
    static char[][] A;
    static char Y = 'Y', R = 'R', E = 'E', X = 'X';
    
    private static boolean canSurvive(int l) {
        int y = -1;
        if (l == 0) {
            for (int i = 0; i < W; ++i) {
                if (A[l][i] == Y) {
                    A[l][i] = E;
                    y = i;
                    break;
                }
            }
        }
        for (int i = 0; i < W; ++i) {
            if (A[l][i] == R)
                continue;
            if (A[l + 1][i] != X) {
                continue;
            }
            A[l][i] = X;
            if (i > 0 && A[l][i - 1] != R) 
                A[l][i - 1] = X;
            if (i < W - 1 && A[l][i + 1] != R) 
                A[l][i + 1] = X;
        }
        if (l == 0)
            return A[0][y] == X;
        return canSurvive(l - 1);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(FallingRocks.class.getResourceAsStream("FallingRocks.in"));
        W = scanner.nextInt();
        H = scanner.nextInt() + 1;
        A = new char[H][W];
        for (int i = 0; i < H - 1; ++i) 
        {
            A[i] = scanner.next().toCharArray();
        }
        A[H - 1] = String.format("%" + W + "s", "").replace(' ', X).toCharArray();
        System.out.println(canSurvive(H - 2)? "YES": "NO");
        scanner.close();
    }

}
