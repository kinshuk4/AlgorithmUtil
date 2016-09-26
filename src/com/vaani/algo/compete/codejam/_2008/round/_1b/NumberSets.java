/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Code jam
 * Round 1B 2008
 * Problem B. Number Sets
 * Small input: correct
 * Large input: time limit
 * 
 * Note: current code relies on weighted quick-union data
 * structure. Initially it was based on quick-find but it 
 * was slow. Eventually it seems that most reasonable is 
 * to build graph and then count connected components.
 * 
 * You start with a sequence of consecutive integers. You 
 * want to group them into sets.
 * You are given the interval, and an integer P. Initially, 
 * each number in the interval is in its own set.
 * Then you consider each pair of integers in the interval.
 * If the two integers share a prime factor which is at 
 * least P, then you merge the two sets to which the two 
 * integers belong.
 * How many different sets there will be at the end of 
 * this process?
 * 
 * Input
 * 
 * One line containing an integer C, the number of test 
 * cases in the input file.
 * For each test case, there will be one line containing 
 * three single-space-separated integers A, B, and P. A 
 * and B are the first and last integers in the interval, 
 * and P is the number as described above.
 * 
 * Output
 * 
 * For each test case, output one line containing the 
 * string "Case #X: Y" where X is the number of the test 
 * case, starting from 1, and Y is the number of sets.
 *
 * Sample
 * 
 * Input
 * 2
 * 10 20 5
 * 10 20 3
 * 
 * Output
 * Case #1: 9
 * Case #2: 7
 * 
 */

package com.vaani.algo.compete.codejam._2008.round._1b;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class NumberSets {

    @SuppressWarnings({ "unchecked", "serial" })
    static long countSets(long a, long b, int p) {
        // maps number to group
        int[] s = new int[(int) (b - a + 1)];
        IntStream.range(0, s.length).forEach((i) -> s[i] = i);
        int[] w = new int[(int) (b - a + 1)];
        IntStream.range(0, w.length).forEach((i) -> w[i] = 1);
        // maps group to numbers
        List<Integer>[] invs = new List[s.length];
        Arrays.setAll(invs, (i) -> new LinkedList<Integer>(){{add(i);}});
        Scanner scanner = new Scanner(NumberSets.class.getResourceAsStream("primes"));
        int prime = scanner.nextInt();
        while (prime < p)
            prime = scanner.nextInt();
        while (b - a > prime && scanner.hasNextInt()) {
            long n = a;
            p = prime;
            prime = scanner.nextInt();
            if (n % p > 0) {
                n = n / p * p + p;
            }
            if (n >= b)
                continue;
            int g = findGroup(s, (int) (n - a));
            do {
                changeGroup(s, w, invs, findGroup(s, (int) (n - a)), g);
                n += p;
            } while (n <= b);
        }
        scanner.close();
        return IntStream.range(0, invs.length).filter((i) -> !invs[i].isEmpty()).count();
    }

    private static void changeGroup(int[] s, int[] w, List<Integer>[] invs, int curGroup, int newGroup) {
        if (curGroup == newGroup)
            return;
        if (w[curGroup] > w[newGroup]) {
            int t = curGroup;
            curGroup = newGroup;
            newGroup = t;
        }
        s[curGroup] = newGroup;
        invs[newGroup].addAll(invs[curGroup]);
        invs[curGroup].clear();
    }

    private static int findGroup(int[] s, int i) {
        int curGroup = s[i];
        while (curGroup != i) {
            i = curGroup;
            curGroup = s[i];
        }
        return curGroup;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/tmp/in"));
        int T = scanner.nextInt();
        for (int t = 1 ; t <= T; ++t) {
            long a = scanner.nextLong();
            long b = scanner.nextLong();
            int p = scanner.nextInt();
            System.out.format("Case #%d: %s\n", t, countSets(a, b, p));
        }
        scanner.close();
    }

}
