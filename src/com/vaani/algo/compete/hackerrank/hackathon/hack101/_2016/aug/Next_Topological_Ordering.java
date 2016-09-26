/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 24/08/2016
 * 
 * Hacker rank
 * 101 Hack Aug 2016
 * Problem: Next Topological Ordering
 * Status: accepted
 * 
 * For a directed acyclic graph (DAG), a topological ordering is a 
 * linear ordering of its vertices such that for every directed edge
 * from vertex u to vertex v (i.e., edge u -> v), u is listed before v.
 * A topological ordering a1, a2, .., an is considered lexicographically 
 * smaller than another ordering, b1, b2, .., bn, if ai < bi, for the 
 * first index i where ai and bi differ.
 * Given a DAG and a topological ordering, p, find the smallest topological 
 * ordering that is also lexicographically greater than p.
 * 
 * Note: Each pair of vertices have at most one directed edge between them. 
 * 
 * Input Format
 * 
 * The first line contains two space-separated integers describing the 
 * respective values of n (the number of vertices) and m (the number of 
 * directed edges) in the DAG.
 * Each of the m subsequent lines contains a pair of space-separated integers,
 * u and v, describing a directed edge from vertex u (the first value) to 
 * vertex v (the second value).
 * The last line contains a permutation n of distinct space-separated positive 
 * integers (where each integer is 1..n denoting topological ordering p.
 * 
 * Output Format
 * 
 * Print n space-separated integers denoting the smallest topological ordering 
 * that is also lexicographically greater than p; if p is already the 
 * lexicographically largest topological ordering, print -1 instead.
 * 
 * Sample Input
 * 
5 5
1 3
2 3
3 4
2 5
5 4
1 2 5 3 4
 *
 * Sample Output
 * 
2 1 3 5 4
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.hack101._2016.aug;

import static java.util.Arrays.setAll;
import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.IntStream.range;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Next_Topological_Ordering {
    
    static List<Set<Integer>> G;
    static int[] P;
    
    /*
     * Sorts subsequence of DAG in a[s..n] inplace.
     * Edges to itself are not supported
     */
    static void topologicalLexicographicSort(List<Set<Integer>> g, int[] a, int s) {
        Map<Integer, Integer> degrees = stream(a).skip(s)
            .boxed()
            .flatMap(v -> G.get(v).stream())
//            .peek(System.out::println)
            .collect(groupingBy(identity(), summingInt(i -> 1)));
        Queue<Integer> q = new PriorityQueue<>();
        q.addAll(stream(a).skip(s)
                .filter(v -> !degrees.containsKey(v))
                .boxed()
                .collect(Collectors.toList()));
        while (!q.isEmpty()) {
            Integer v = q.remove();
            for (int u: g.get(v)) {
                degrees.computeIfPresent(u, (vv, cnt) -> cnt - 1);
                if (degrees.get(u) == 0) {
                    q.add(u);
                }
            }
            a[s++] = v;
        }
    }
    
    /*
     * Finds next after A lexicographic arrangement of a DAG g.
     * Edges to itself are not supported
     */
    static int[] nextLexicographic(List<Set<Integer>> g, int[] A) {
        int[] a = A.clone();
        int i = a.length - 2;
        TreeSet<Integer> q = new TreeSet<>();
        q.add(a[i + 1]);
        for (; i >= 0; i--) {
            q.removeAll(g.get(a[i]));
            if (!q.isEmpty() && q.higher(a[i]) != null)
                break;
            q.add(a[i]);
        }
        if (i < 0)
            return A;
        int k = a[i];
        topologicalLexicographicSort(g, a, i);
        int ki = i;
        Set<Integer> s = new HashSet<>();
        for (int j = i; j < a.length; j++) {
            int v = a[j];
            s.addAll(g.get(v));
            if (s.contains(v))
                continue;
            if (v <= k)
                continue;
            k = v;
            ki = j;
            break;
        }
        if (ki == i)
            return A;
        for (int j = ki; j > i; j--) {
            a[j] = a[j - 1];
        }
        a[i] = k;
        topologicalLexicographicSort(g, a, i + 1);
        return a;
    }
    
    static void solve() {
        int[] a = nextLexicographic(G, P);
        if (a == P)
            System.out.println(-1);
        else
            System.out.println(stream(a)
                .mapToObj(i -> String.valueOf(i + 1))
                .collect(Collectors.joining(" ")));
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Next_Topological_Ordering.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Next_Topological_Ordering.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            G = Stream.generate(HashSet<Integer>::new)
                    .limit(n)
                    .collect(Collectors.toList());
            range(0, scanner.nextInt()).forEach(e -> {
                int v = scanner.nextInt() - 1;
                int u = scanner.nextInt() - 1;
                if (v == u) return;
                G.get(v).add(u);  
            });
            P = new int[n];
            setAll(P, i-> scanner.nextInt() - 1);
            solve();
        }
        scanner.close();
    }

}
