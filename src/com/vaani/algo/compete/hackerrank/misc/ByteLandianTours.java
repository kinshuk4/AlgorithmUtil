/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 10/12/2015
 *
 * Hacker rank
 * Problem: ByteLandian Tours
 * Status: accepted / timeout
 * 
 * Problem
 * 
 * The country of Byteland contains N cities and N - 1 bidirectional roads between them 
 * such that there is a path between any two cities. The cities are numbered (0,...,N - 1). 
 * The people were very unhappy about the time it took to commute, especially salesmen who 
 * had to go about every city selling goods. So it was decided that new roads would be built 
 * between any two "somewhat near" cities. Any two cities in Bytleland that can be reached 
 * by traveling on exactly two old roads are known as "somewhat near" each other.
 * Now a salesman situated in city 0, just like any other typical salesman, has to visit 
 * all cities exactly once and return back to city 0 in the end. In how many ways can he 
 * do this?
 * 
 * Input Format
 * 
 * The first line contains the number of test cases T. T test cases follow. The first line 
 * contains N, the number of cities in Byteland. The following N - 1 lines contain the 
 * description of the roads. The ith line contains two integers ai and bi, meaning that 
 * there was originally a road connecting cities with numbers ai and bi.
 * 
 * Output Format
 * 
 * Output T lines, one corresponding to each test case containing the required answer for 
 * that test case. Since the answers can be huge, output them modulo 1000000007
 * 
 * Input
 * 
2
3
0 1
1 2
5
0 1
1 2
2 3
2 4
 * 
 * Output
 * 
2
4
 * 
 */
package com.vaani.algo.compete.hackerrank.misc;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class ByteLandianTours {

    static Set<Integer>[] G;
    
    static class Pair {
        int l;
        int v;
        Pair(int l, int v) {
            this.l = l;
            this.v = v;
        }
    }
    
    static int dfs() {
        Stack<Pair> s = new Stack<>();
        s.add(new Pair(0, 0));
        Stack<Integer> path = new Stack<>();
        boolean[] visited = new boolean[G.length];
        int res = 0;
        while (!s.isEmpty()) {
            Pair p = s.pop();
            while (p.l != path.size()) {
                visited[path.pop()] = false;
            }
            path.push(p.v);
            Consumer<Pair> f = a -> {
                s.add(a);
                visited[p.v] = true;
            };
            int adj = (int) G[p.v].stream().
                    filter(a -> !visited[a]).
                    map(a -> new Pair(path.size(), a)).
                    peek(f).
                    count();
            if (adj == 0) {
                if (path.size() == G.length && G[p.v].contains(0)) {
                    //System.out.println(path);
                    res++;
                }
            }
        }
        return res;
    }
    
    @SuppressWarnings("unchecked")
    private static void moreEdges() {
        Set<Integer>[] newG = new Set[G.length];
        Arrays.setAll(newG, i -> new HashSet<>(G[i]));
        IntStream.range(0, G.length).forEach(v -> {
            List<Integer> l = new ArrayList<>();
            for (int adj: G[v]) {
//                if (adj < v) continue;
                G[adj].stream().filter(u -> u > v).forEach(u -> {
                    l.add(u);
                    newG[u].add(v);
                });
            }
            newG[v].addAll(l);
        });
        G = newG;
    }
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(ByteLandianTours.class.getResourceAsStream("ByteLandianTours.in"));
        int T = scanner.nextInt();
        for (int i = 0; i < T; ++i) {
            int N = scanner.nextInt();
            G = new Set[N];
            Arrays.setAll(G, HashSet::new);
            for (int n = 0; n < N - 1; ++n) {
                int v = scanner.nextInt();
                int a = scanner.nextInt();
                G[v].add(a);
                G[a].add(v);
            }
            moreEdges();
            System.out.println(dfs());
        }
        scanner.close();
    }

}
