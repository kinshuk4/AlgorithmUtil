/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 10/04/2015
 * 
 * Code jam
 * Round 1C 2008
 * Problem C. Increasing Speed Limits
 * Small input: correct
 * Large input: correct
 * 
 * Problem
 * 
 * You were driving along a highway when you got caught by the road police for
 * speeding. It turns out that they've been following you, and they were amazed
 * by the fact that you were accelerating the whole time without using the brakes!
 * And now you desperately need an excuse to explain that.
 * 
 * You've decided that it would be reasonable to say "all the speed limit signs I
 * saw were in increasing order, that's why I've been accelerating". The police
 * officer laughs in reply, and tells you all the signs that are placed along the
 * segment of highway you drove, and says that's unlikely that you were so lucky just
 * to see some part of these signs that were in increasing order.
 * 
 * Now you need to estimate that likelihood, or, in other words, find out how many
 * different subsequences of the given sequence are strictly increasing. The empty
 * subsequence does not count since that would imply you didn't look at any speed 
 * limits signs at all!
 * 
 * For example, (1, 2, 5) is an increasing subsequence of (1, 4, 2, 3, 5, 5), and we
 * count it twice because there are two ways to select (1, 2, 5) from the list. 
 * 
 * 
 * Input
 * 
 * The first line of input gives the number of cases, N. N test cases follow. The
 * first line of each case contains n, m, X, Y and Z each separated by a space. n will
 * be the length of the sequence of speed limits. m will be the length of the generating
 * array A. The next m lines will contain the m elements of A, one integer per line 
 * (from A[0] to A[m-1]).
 * 
 * Using A, X, Y and Z, the following pseudocode will print the speed limit sequence in 
 * order. mod indicates the remainder operation. 
 * 
 * for i = 0 to n-1
 *   print A[i mod m]
 *   A[i mod m] = (X * A[i mod m] + Y * (i + 1)) mod Z
 * 
 * 
 * Output
 * 
 * For each test case you should output one line containing "Case #T: S" (quotes for clarity)
 * where T is the number of the test case and S is the number of non-empty increasing 
 * subsequences mod 1 000 000 007. 
 * 
 * Sample
 * 
 * Input
 * 
 * 2
 * 5 5 0 0 5
 * 1
 * 2
 * 1
 * 2
 * 3
 * 6 2 2 1000000000 6
 * 1
 * 2
 * 
 * Output
 * Case #1: 15
 * Case #2: 13
 * 
 */

package com.vaani.algo.compete.codejam._2008.round._1c;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;


public class IncreasingSpeedLimits {

    static long[] limits(int n, int m, long X, long Y, long Z, long[] A) {
        long[] res = new long[n];
        for (int i = 0; i < n; ++i) {
            res[i] = A[i % m];
            A[i % m] = (X * A[i % m] + Y * (i + 1)) % Z;
        }
//        LongStream.range(0, n).forEach((i) -> System.out.print(res[(int) i] + " "));
//        System.out.println();
        return res;
    }

    /*
     * polynomial time implementation
     */
    
    static void calc(long[] A, long[] C, int n) {
        C[n] = 1;
        for (int i = 0; i < n; i++) {
            if (A[n] > A[i])
                C[n] += C[i];
            if (C[n] > 1000000007L) {
                C[n] -= 1000000007L;
            }
        }
    }
    
    @SuppressWarnings("unused")
    private static long calcPolynomialTime(long[] A) {
        long[] C = new long[A.length];
        for (int i = 0; i < A.length; ++i) {
                calc(A, C, i);
//                System.out.println(C[i]);
        }
        BigInteger r = BigInteger.ZERO;
        for (int i = 0; i < C.length; ++i) {
            r = r.add(BigInteger.valueOf(C[i]));
        }
        return r.mod(new BigInteger("1000000007")).longValue();
    }

    /*
     * linearithmic implementation
     */
    
    static class Node {
        Node l, r;
        long k;
        long v; // number of sequences with numbers in left subtree and current one
        long d; // number of sequences which ends on this node's number only
    }
    
    static Node makeBST(long[] a, int s, int e) {
        if (e >= a.length) return null;
        if (e < s) return null;
        int mid = (s + e) / 2;
        Node node = new Node();
        node.k = a[mid];
        node.l = makeBST(a, s, mid - 1);
        node.r = makeBST(a, mid + 1, e);
        return node;
    }
    
    static void print(Node n, int offset) 
    {
        if (n == null) 
            return;
        IntStream.range(0, offset).forEach((i) -> 
            System.out.print(' '));
        System.out.println(n.k);
        offset++;
        print(n.l, offset);
        print(n.r, offset);
    }
    
    static long SUM = 0;
    
    private static long add(Node t, long n, long s) {
        long r = 0;
        if (s > 1000000007L) {
            s %= 1000000007L;
        }
        if (t.k == n) {
            r = 1 + (t.v - t.d) + s;
            t.v += r;
            t.d += r;
            SUM += r;
            if (SUM > 1000000007L) {
                SUM %= 1000000007L;
            }
        }
        if (t.k > n) {
            r = add(t.l, n, s);
            t.v += r;
        }
        if (t.k < n) {
            r = add(t.r, n, s + t.v);
        }
        return r;
    }

    private static long calcLinearithmic(long[] A) 
    {
        SUM = 0;
        long[] S = Arrays.copyOf(A, A.length);
        Arrays.sort(S);
        S = Arrays.stream(S).distinct().toArray();
        Node t = makeBST(S, 0, S.length - 1);
//        print(t, 0);
        IntStream.range(0, A.length).forEach((i) -> {
            add(t, A[i], 0);/*System.out.println(SUM + " " + t.v);*/});
        return SUM;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/tmp/inl"));
        int T = scanner.nextInt();
        for (int t = 1 ; t <= T; ++t) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            long X = scanner.nextLong();
            long Y = scanner.nextLong();
            long Z = scanner.nextLong();
            long[] A = new long[m];
            Arrays.setAll(A, (i) -> scanner.nextLong());
            A = limits(n, m, X, Y, Z, A);
            System.out.format("Case #%d: %s\n", t, 
                    calcLinearithmic(/*new long[]{1,2,1,2,3,3}*/A));
        }
        scanner.close();
    }

}



