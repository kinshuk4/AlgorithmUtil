/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 21/06/2016
 * 
 * Hacker rank
 * World CodeSprint #4
 * Problem: Roads in Hackerland
 * Status: accepted
 * 
 * 
 * John lives in HackerLand, a country with N cities and M bidirectional 
 * roads. Each of the roads has a distinct length, and each length is a 
 * power of two (i.e. 2, raised to some exponent). It's possible for John 
 * to reach any city from any other city.
 * Given a map of HackerLand, can you help John determine the sum of the 
 * minimum distances between each pair of cities? Print your answer in 
 * binary representation. 
 * 
 * Input Format
 * 
 * The first line contains two space-seperated integers denoting N (the 
 * number of cities) and M (the number of roads), respectively.
 * Each line i of the M subsequent lines contains the respective values 
 * of Ai, Bi, and Ci as three space-separated integers. These values 
 * define a bidirectional road between cities Ai and Bi having length 2^Ci.
 * 
 * Output Format
 * 
 * Find the sum of minimum distances of each pair of cities and print the 
 * answer in binary representation. 
 * 
 * Sample Input
 * 
5 6
1 3 5
4 5 0
2 1 3
3 2 1
4 3 4
4 2 2
 *
 * Sample Output
 * 
1000100
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.worldcodesprint._4;

import com.vaani.algo.ds.core.graph.disjoint.DisjointSet;

import java.io.FileNotFoundException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Roads_in_HackerLand {

    @SuppressWarnings("serial")
    static class Pair<K, V> extends SimpleEntry<K, V> {
        public Pair(K k, V v) {
            super(k, v);
        }
    }
    
    @SuppressWarnings("serial")
    static class Edge extends Pair<Integer, Integer> {
        public Edge(Integer key, Integer value) {
            super(key, value);
        }
    }
    
    static List<Integer>[] G;
    static List<Integer>[] W;
    static Pair<Integer, Edge>[] E;
    
    static int parseInt(String binary) {
        int r = 0;
        int c = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1')
                r |= 1 << c;
            c++;
        }
        return r;
    }

    @SuppressWarnings({ "unchecked" })
    static void buildGraph(Scanner scanner, int maxNumOfNodes, int maxNumOfEdges) {
        G = new List[maxNumOfNodes];
        W = new List[maxNumOfNodes];
        E = new Pair[maxNumOfEdges];
        int capacity = 100;
        for (int i = 0; i < G.length; i++) {
            G[i] = new ArrayList<>(capacity);
            W[i] = new ArrayList<>(capacity);
        }
        for (int i = 0; i < maxNumOfEdges; i++) {
            int v = scanner.nextInt() - 1;
            int u = scanner.nextInt() - 1;
            int w = scanner.nextInt();
            G[v].add(u);
            G[u].add(v);
            W[v].add(w);
            W[u].add(w);
            E[i] = new Pair<>(w, new Edge(v, u));
        }
    }

    static void buildGraph(List<Pair<Integer, Edge>> edges, int maxNumOfNodes) {
        for (int i = 0; i < G.length; i++) {
            G[i].clear();
            W[i].clear();
        }
        for (Pair<Integer, Edge> p: edges) {
            int w = p.getKey();
            int v = p.getValue().getKey();
            int u = p.getValue().getValue();
            G[v].add(u);
            G[u].add(v);
            W[v].add(w);
            W[u].add(w);
        }
    }
    
    static List<Pair<Integer, Edge>> kruskalMst() {
        Arrays.sort(E, (p1, p2) -> p1.getKey() - p2.getKey());
        DisjointSet ds = new DisjointSet(100_000);
        List<Pair<Integer, Edge>> mst = new ArrayList<>(G.length);
        for (Pair<Integer, Edge> p: E) {
            Edge e = p.getValue();
            if (!ds.union(e.getValue(), e.getKey()))
                continue;
            mst.add(p);
        }
        return mst;
    }
    
    /**
     * Performs summation of numbers which are specified in 
     * exponentiation form 2^m and writes the result in binary
     * format into the input array.
     * Input array should be large enough to hold the result.
     * 
     * @param exps array of input numbers are stored here in
     * the form exps[m] = how many 2^m input numbers
     * 
     */
    static void sum(long[] exps) {
        int s = 0;
        for (int i = 0; i < exps.length; i++) {
            if (exps[i] == 0) continue;
            if ((exps[i] & 1) == 1) {
                exps[i + 1] += (exps[i] - 1) >> 1;
                exps[i] = 1;
            } else {
                exps[i + 1] += exps[i] >> 1;
                exps[i] = 0;
            }
            s = i;
        }
        for (int i = s; i >= 0; i--) {
            System.out.print(exps[i]);
        }
    }
    
    static long[] EXPS = new long[200_050];
    static boolean[] V;
    
    static long dfsTree(int v) {
        long c = 0;
        V[v] = true;
        for (int i = 0; i < G[v].size(); i++) {
            int u = G[v].get(i);
            if (V[u]) continue;
            long k = dfsTree(u);
            c += k;
            EXPS[W[v].get(i)] = k * (G.length - k);
        }
        return c + 1;
    }
    
    static void apspByMst(long[] exps) {
        List<Pair<Integer, Edge>> mst = kruskalMst();
        buildGraph(mst, G.length);
        V = new boolean[G.length];
        dfsTree(0);
    }
    
    static void solve() {
        apspByMst(EXPS);
        sum(EXPS);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Roads_in_HackerLand.class.getResourceAsStream("Roads_in_HackerLand.in"));
        buildGraph(scanner, scanner.nextInt(), scanner.nextInt());        
        solve();
    }

}
