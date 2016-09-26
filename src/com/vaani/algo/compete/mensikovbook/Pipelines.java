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
 * Given start point, end point and limited set of pipes of different length. Need to
 * find min number of pipes needed to connect start/end points or print -1 if it is
 * not possible.
 * 
 * x1, y1, x2, y2, K, L1, .., Lk, C1, .., Ck
 * 
 * Where 1 ≤ K ≤ 4, 1 ≤ x1, y1, x2, y2, Li ≤ 1000, 1 ≤ Ci ≤ 10
 * 
 * Input
 *
20 10 60 50 2 70 30 2 2
 * 
 * Output
 * 
4
 * 
 */
package com.vaani.algo.compete.mensikovbook;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Pipelines {

    static Point A, B;
    static short[] L;
    static byte[] C;
    static byte[][] F = new byte[1000][1000];
    static byte[][][] R = new byte[1000][1000][4];

    static void dijkstra()
    {
        F[A.x][A.y] = 0;
        System.arraycopy(C, 0, R[A.x][A.y], 0, C.length);
        Queue<Point> q = new PriorityQueue<>(1000, (p1, p2) -> F[p1.x][p1.y] - F[p2.x][p2.y]);
        q.add(A);
        while (!q.isEmpty()) {
            Point v = q.poll();
            //System.out.println("-" + v + " " + Arrays.toString(cv.pipes));
            for (int i = 0; i < L.length; ++i) {
                if (R[v.x][v.y][i] == 0) continue;
                final int fi = i;
                Function<Point, Void> relax = u -> {
                    if (F[u.x][u.y] != Byte.MAX_VALUE)
                        q.remove(u);
                    System.arraycopy(R[v.x][v.y], 0, R[u.x][u.y], 0, R[v.x][v.y].length);
                    R[u.x][u.y][fi]--;
                    F[u.x][u.y] = (byte) (F[v.x][v.y] + 1);
                    //System.out.println(u + " " + Arrays.toString(cu.pipes));
                    q.add(u);
                    return null;
                };
                IntStream.of(v.x - L[i], v.x + L[i]).
                    filter(x -> x >= 0 && x < F[0].length).
                    filter(x -> F[x][v.y] > F[v.x][v.y] + 1).
                    forEach(x -> relax.apply(new Point(x, v.y)));
                IntStream.of(v.y - L[i], v.y + L[i]).
                    filter(y -> y >= 0 && y < F.length).
                    filter(y -> F[v.x][y] > F[v.x][v.y] + 1).
                    forEach(y -> relax.apply(new Point(v.x, y)));
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null?
                new Scanner(System.in): new Scanner(Pipelines.class.getResourceAsStream("Pipelines.in"));
        A = new Point(scanner.nextInt() - 1, scanner.nextInt() - 1);
        B = new Point(scanner.nextInt() - 1, scanner.nextInt() - 1);
        int n = scanner.nextInt();
        L = new short[n];
        C = new byte[n];
        IntStream.range(0, n).forEach(i -> L[i] = scanner.nextShort());
        IntStream.range(0, n).forEach(i -> C[i] = scanner.nextByte());
        IntStream.range(0, F.length).forEach(i ->
            IntStream.range(0, F[0].length).forEach(j -> F[i][j] = Byte.MAX_VALUE));
        dijkstra();
        System.out.println(F[B.x][B.y] == Byte.MAX_VALUE? "-1": F[B.x][B.y]);
        scanner.close();
    }

}
