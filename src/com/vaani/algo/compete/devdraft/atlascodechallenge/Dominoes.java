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
 * Problem: Dominoes
 * Status: accepted
 * 
 * Problem
 * 
 * We have a game app that involves knocking over dominoes of different heights that are arranged 
 * in a line. When dominoes fall, they may knock over other dominoes, which, as you might expect, 
 * may in turn knock over even more dominoes.
 * 
 * A player may knock over a domino either to the left or to the right. Let dominoHeights[x] denote 
 * the height of the domino at position x. The effect of knocking over to the right a domino at 
 * position x is that all dominoes at positions x + 1 to x + dominoHeights[x], inclusive, are 
 * knocked over to the right as well. These dominoes may then knock over even more dominoes until 
 * nothing else is in range to be knocked over. 
 * Similarly, the effect of knocking over the same domino to the left is that all dominoes at
 * positions x - 1 to x - dominoHeights[x], inclusive, are knocked over to the left.
 * 
 * Input
 * 
 * The input should be read from standard input. The first line contains a single integer denoting 
 * N, the length of the line of dominoes. Each domino will have a position
 * between 0 and N - 1.
 * 
 * The second line contains N space-separated integers. Counting from zero, the i-th integer 
 * denotes the height of the domino at position i, or 0 if there is no domino at that
 * position. All domino heights are between 1 and 2,147,483,647.
 * 
 * Test case set A (code correctness metric): 0 <= N <= 100
 * 
 * Test case set 8 (algorithmic problem solving metric): Your code will be run against increasingly 
 * larger N, up to N <= 500000, testing the efficiency of your solution. Your
 * score will increase in proportion to the size of the test cases you can handle.
 * 
 * Output
 * 
 * The output should be written to standard output and consists of two lines. On the first line, 
 * output N space-separated integers, where the i-th (counting from 0) integer
 * denotes the right cascade distance of the domino at position i, or 0 for positions that do not 
 * have a domino. On the second line, output the same data for left cascade distances.
 * 
 */
package com.vaani.algo.compete.devdraft.atlascodechallenge;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Dominoes {

    static int N;
    static int[] D;
    static Node T;
    
    static class Node {
        Node l, r;
        int s, e, m;
    }

    static void reverse(int[] a) {
        for(int i = 0; i < a.length / 2; i++)
        {
            int temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }

    static Node makeIntervalTree(int[] a, int s, int e) 
    {
        if (e >= a.length)
            return null;
        if (e < s) return null;
        int mid = (s + e) / 2;
        Node node = new Node();
        node.s = mid;
        node.e = mid + a[mid];
        node.l = makeIntervalTree(a, s, mid - 1);
        node.r = makeIntervalTree(a, mid + 1, e);
        int m = node.e;
        if (node.l != null)
            m = Math.max(m, node.l.m);
        if (node.r != null)
            m = Math.max(m, node.r.m);
        node.m = m;
        return node;
    }
    
    static void print(Node n, int offset) 
    {
        if (n == null) 
            return;
        IntStream.range(0, offset).forEach((i) -> System.out.print(' '));
        System.out.format("%d %d (%d)\n", n.s, n.e, n.m);
        offset++;
        print(n.l, offset);
        print(n.r, offset);
    }
    
    static int calc(Node n, int s, int e) {
        if (n == null) return e;
        if (n.m < e) return e;
        if (n.m == e) return e;
        if (s < n.s) {
            e = calc(n.l, s, e);
            if (e >= n.s)
                return calc(n.r, s, Math.max(e, n.e));
            return e;
        }
        if (s <= n.s)
            return calc(n.r, s, Math.max(e, n.e));
        else
            return calc(n.r, s, e);
    }

    static int[] solve() {
        T = makeIntervalTree(D, 0, D.length - 1);
        //print(T, 0);
        return IntStream.
                range(0, D.length).
                map(i -> calc(T, i, i + D[i]) - i).
                toArray();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Dominoes.class.getResourceAsStream("Dominoes.in"));
        N = scanner.nextInt();
        D = new int[N];
        Arrays.setAll(D, i -> scanner.nextInt());
        Function<int[], String> f = a -> Arrays.stream(a).mapToObj(String::valueOf).
                collect(Collectors.joining(" "));
        int[] res = solve();
        System.out.println(f.apply(res));
        reverse(D);
        res = solve();
        reverse(res);
        System.out.println(f.apply(res));
        scanner.close();
    }

}
