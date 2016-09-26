/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 23/06/2016
 * 
 * Problem
 * 
 * Given input string and set of transformations. Apply given
 * transformations until you get a cycle or until no 
 * transformation can be made.
 * Transformations should be applied as follows:
 * 
 * Take first transformation and find first possible substring
 * for it:
 * - If substring not found go to next transformation. If there is
 * no one - stop.
 * - otherwise apply transformation and repeat
 * 
 * Print how many transformations can be done until getting cycle,
 * and length of the cycle (0 - if it is absent).
 * 
 * Input
 *
aaaa
a->c
c->b
b->a
 * 
 * Output
 * 
8 3
 * 
 */

package com.vaani.algo.compete.mensikovbook;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class SuffixArray {

    static class Prefix implements Comparable<Prefix> {
        // a - index of the previous group
        // b - index of the current group
        int a, b;
        // index where prefix starts in A
        int p;
        @Override
        public int compareTo(Prefix o) {
            if (a == o.a) return b - o.b;
            return a - o.a;
        }
        @Override
        public String toString() {
            return String.format("%d<%d,%d>", p, a, b);
        }
    }
    
    char[] A;
    // maps suffixes of the input array A to their
    // positions in SA
    int[] ASA;
    // suffix array
    int[] SA;
    
    SuffixArray(char[] a) {
        A = a;
        ASA = new int[a.length];
        suffixArray(a, 1);
        SA = invertedIndex(ASA);
    }
    
    private int[] invertedIndex(int[] a) {
        int[] inv = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            inv[a[i]] = i;
        }
        return inv;
    }

    int[] find(char[] a) {
        return find(a, 0, 0, SA.length - 1);
    }
    
    private int[] find(char[] a, int i, int s, int e) {
        int[] range = findRange(a[i], i, s, e);
        if (range == null) return null;
        if (i == a.length - 1) return range;
        range = find(a, i + 1, range[0], range[1]);
        if (range == null) return range;
        return range;
    }
    
    private int[] findRange(char ch, int i, int s, int e) {
        if (e < s)
            return null;
        if (e == s)
            if (SA[e] + i < A.length && A[SA[e] + i] == ch)
                return new int[]{s, s};
            else
                return null;
        int m = (e - s) / 2 + s;
        char mch = SA[m] + i < A.length? A[SA[m] + i]: 0;
        if (mch < ch)
            return findRange(ch, i, m + 1, e);
        if (mch > ch)
            return findRange(ch, i, s, m - 1);
        if (mch == ch) {
            int[] l = null;
            if (m != 0)
                l = findRange(ch, i, s, m - 1);
            if (l == null)
                l = new int[]{m, m};
            int[] r = null;
            if (m != SA.length - 1)
                r = findRange(ch, i, m + 1, e);
            if (r == null)
                r = new int[]{m, m};
            return new int[]{l[0], r[1]};
        }
        return null;
    }
    
    /**
     * Prefix doubling implementation.
     * 
     * @param n length of a new prefix
     */
    private void suffixArray(char[] A, int n) {
        if (n >= A.length * 2) {
            return;
        }
        Prefix[] P = new Prefix[ASA.length];
        for (int i = 0; i < ASA.length; ++i) {
            Prefix p = new Prefix();
            p.a = ASA[i];
            int j = i + n / 2;
            if (j < A.length)
                p.b = n == 1? A[i]: ASA[j];
            else
                p.b = -i;
            p.p = i;
            P[i] = p;
        }
        Arrays.sort(P);
        int a = P[0].a;
        int b = P[0].b;
        int c = 0;
        for (int i = 0; i < P.length; ++i) {
            ASA[P[i].p] = c;
            if (P[i].a == a && P[i].b == b)
                continue;
            ASA[P[i].p] = ++c;
            a = P[i].a;
            b = P[i].b;
        }
        suffixArray(A, n * 2);
    }
}

public class MarkovCycle {

    static char[] A;
    static List<String> FROM = new ArrayList<>();
    static List<String> TO = new ArrayList<>();

    static void solve() {
        Map<String, Integer> m = new HashMap<>();
        int c = 0;
        String cur = new String(A);
        while (!m.containsKey(cur)) {
            SuffixArray sa = new SuffixArray(A);
            int[] range = null;
            for (int i = 0; i < FROM.size(); i++) {
                range = sa.find(FROM.get(i).toCharArray());
                if (range == null) continue;
                m.put(cur, c++);
                System.arraycopy(TO.get(i).toCharArray(), 0, A, sa.SA[range[1]], TO.get(i).length());
                cur = new String(A);
//                System.out.println(cur);
                break;
            }
            if (range == null) {
                System.out.format("%d %d\n", c, 0);
                return;
            }
        }
        int l = c - m.get(cur);
        System.out.format("%d %d\n", c - l, l);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null?
                new Scanner(System.in): new Scanner(MarkovCycle.class.getResourceAsStream("MarkovCycle.in"));
        A = scanner.nextLine().toCharArray();
        while (scanner.hasNextLine()) {
            String[] s = scanner.nextLine().split("->");
            if (s.length < 2) break;
            FROM.add(s[0].trim());
            TO.add(s[1].trim());
        }
        solve();
        scanner.close();
    }
    
}
