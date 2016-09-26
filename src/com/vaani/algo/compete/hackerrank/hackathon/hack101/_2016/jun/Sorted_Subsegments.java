/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 12/07/2016
 * 
 * Hacker rank
 * 101 Hack Jun 2016
 * Problem: Sorted Subsegments
 * Status: accepted
 * 
 * Consider an array A = [a0, .., aN] of n integers. We perform q 
 * queries of the following type on A:
 * - Sort all the elements in the subsegment al..ar
 * Given A, can you find and print the value at index k (where 
 * 0 <= k <= n) after performing q queries? 
 * 
 * Input Format
 * 
 * The first line contains three positive space-separated integers 
 * describing the respective values of n (the number of integers in A),
 * q (the number of queries), and k (an index in A).
 * The next line contains space-separated integers describing the 
 * respective values of a0, .., aN-1.
 * Each line j of the q subsequent lines contain two space-separated 
 * integers describing the respective lj and rj values for query j.
 * 
 * Output Format
 * 
 * Print a single integer denoting the value ak after processing all q
 * queries.
 * 
 * Sample Input
 * 
3 1 1
3 2 1
0 1
 *
 * Sample Output
 * 
3
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.hack101._2016.jun;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

class SegmentTree {
    
    private static final double LOG2 = Math.log(2);
    
    int NOVAL;
    int[] A;
    IntBinaryOperator merge;
    int[] ST;
    
    public SegmentTree(int[] a, int noval, IntBinaryOperator merge) {
        int d = (int) (Math.ceil(Math.log(a.length) / LOG2));
        ST = new int[(int) (2 * Math.pow(2, d))];
        NOVAL = noval;
        Arrays.fill(ST, NOVAL);
        A = a;
        this.merge = merge;
        build(0, 0, A.length - 1);
    }
    
    public int query(int l, int r) {
        return query(0, l, r, 0, A.length - 1);
    }
    
    public int update(int p, int v) {
        return update(0, p, v, 0, A.length - 1);
    }
    
    private int query(int n, int qs, int qe, int s, int e) {
        if (qe < s || qs > e)
            return NOVAL;
        if (s == e)
            return ST[n];
        if (qs <= s && e <= qe)
            return ST[n];
        int m = (s + e) / 2;
        return merge.applyAsInt(query(n * 2 + 1, qs, qe, s, m),
                query(n * 2 + 2, qs, qe, m + 1, e));
    }

    private int update(int n, int p, int v, int s, int e) {
        int m = (s + e) / 2;
        if (s == e && s == p) {
            ST[n] = v;
            return ST[n];
        }
        if (p <= m) {
            ST[n] = merge.applyAsInt(update(n * 2 + 1, p, v, s, m), ST[n * 2 + 2]);
            return ST[n];
        }
        ST[n] = merge.applyAsInt(update(n * 2 + 2, p, v, m + 1, e), ST[n * 2 + 1]);
        return ST[n];
    }

    private int build(int n, int s, int e) {
//        System.out.format("%d %d %d\n", s, e, n);
        if (s == e) {
            ST[n] = A[s];
            return ST[n];
        }
        int m = (s + e) / 2;
        int l = build(n * 2 + 1, s, m);
        int r = build(n * 2 + 2, m + 1, e);
        ST[n] = merge.applyAsInt(l, r);
        return ST[n];
    }
    
}

/*
 * This class implements algorithm to perform range sorting 
 * on the array of bits.
 * In worst case the sort operation is linear:
 *  
 *  O(e - s) + O(logN)
 *  
 * The implementation tries to reuse information about ranges
 * which are sorted already. It means that if first range sort
 * operations took O(e - s) time second one will be faster.
 */
class BitSorter {
   
    SegmentTree ST;
    int C;
    
    BitSorter(int[] bits) {
        ST = new SegmentTree(bits, 0, (a, b) -> a + b);
//        System.out.println(Arrays.toString(ST.ST));
    }

    void sort(int s, int e) {
        C = (e - s + 1) - ST.query(s, e);
        update(0, s, e, 0, ST.A.length - 1);
    }

    private void update(int n, int qs, int qe, int s, int e) {
        if (qe < s || qs > e)
            return;
        if (s == e) {
            ST.ST[n] = 1;
            if (C > 0) {
                C--;
                ST.ST[n] = 0;
            }
            ST.A[s] = ST.ST[n];
            return;
        }
        int stn = ST.ST[n];
        int len = Math.min(e, qe) - Math.max(s, qs) + 1;
        if (stn == 0 && C >= len) {
            C -= len;
            return;
        }
        if (C == 0 && stn == e - s + 1) return;
        int m = (s + e) / 2;
        update(n * 2 + 1, qs, qe, s, m);
        update(n * 2 + 2, qs, qe, m + 1, e);
        ST.ST[n] = ST.merge.applyAsInt(ST.ST[n * 2 + 1], ST.ST[n * 2 + 2]);
    }
    
}

public class Sorted_Subsegments {

    static long[] N;
    static long[] A;
    static int[] S = new int[75_000];
    static int[] E = new int[75_000];
    static int K;
    
    /*
     * Applies lambda for values in range s..e.
     * Lambda return codes:
     *  negative - current value is small
     *  0 or positive - current value is big
     *  Returns latest big value encountered.
     */
    static int binarySearch(int s, int e, IntUnaryOperator oper) {
        if (e - s < 0) return -1;
        int m = (s + e) / 2;
        int r = oper.applyAsInt(m);
        if (r < 0)
            return binarySearch(m + 1, e, oper);
        int res = binarySearch(s, m - 1, oper);
        return res == -1? m: res;
    }
    
    static int countLessThan(int c) {
        int[] n = new int[N.length];
        for (int i = 0; i < N.length; i++) {
            n[i] = N[i] <= c? 0: 1;
        }
//        System.out.println("Candidate " + A[c]);
//        System.out.println(Arrays.toString(n));
        BitSorter bitSorter = new BitSorter(n);
//        System.out.println(bitSorter.ST.query(K, K));
        for (int i = 0; i < S.length; i++) {
            int s = S[i];
            int e = E[i];
            bitSorter.sort(s, e);
//            System.out.println(Arrays.toString(n));
        }
        return bitSorter.ST.query(K, K) == 0? 1: -1;
    }
    
    static long solve() {
        A = Arrays.stream(N).distinct().sorted().toArray();
        for (int i = 0; i < N.length; i++) {
            N[i] = Arrays.binarySearch(A, N[i]);
        }
        return A[binarySearch(0, A.length - 1, Sorted_Subsegments::countLessThan)];
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Sorted_Subsegments.class.getResourceAsStream("Sorted_Subsegments.in"));
        N = new long[scanner.nextInt()];
        S = new int[scanner.nextInt()];
        E = new int[S.length];
        K = scanner.nextInt();
        Arrays.setAll(N, i -> scanner.nextLong());
        IntStream.range(0, S.length).forEach(i -> {
            S[i] = scanner.nextInt();
            E[i] = scanner.nextInt();
        });
        System.out.println(solve());
        scanner.close();
    }

}
