/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 24/05/2016
 *
 * Hacker rank
 * Problem: Matrix
 * Status: accepted
 * 
 * Problem
 * 
 * Machines have again attacked the kingdom of Zions! 
 * The kingdom of Zions has N cities and N-1 bidirectional roads. 
 * There is a unique path between any pair of cities.
 * Morpheus has found out that K Machines are planning to destroy 
 * the whole kingdom. These Machines are initially living in K 
 * different cities of the kingdom and they can launch an attack 
 * at anytime. Morpheus has asked Neo to destroy some of the roads 
 * in the kingdom to disrupt all connection among the Machines. After 
 * destroying the necessary roads there should be no path between any 
 * two Machines.
 * Since the attack may happen at any moment, Neo has to do this task 
 * as fast as possible. Each road in the kingdom takes a certain amount 
 * of time to destroy and only one road can be destroyed at a time.
 * You need to write a program that tells Neo the minimum amount of time 
 * he will require to destroy the necessary roads.
 * 
 * Input Format
 * 
 * The First line of the input contains two, space-separated integers, 
 * N and K. Cities are numbered 0 to N-1.
 * N-1 lines follow, each containing three space-separated integers, x y z, 
 * which means there is a bidirectional road connecting city x and city y, 
 * and to destroy this road it takes z units of time.
 * K lines follow, each containing an integer. The ith integer is the id of 
 * the city in which the ith Machine is currently located.
 * 
 * Output Format
 * 
 * Print in a single line the minimum time required to disrupt the connection 
 * among Machines.
 * 
 * Input
 * 
5 3
2 1 8
1 0 5
2 4 5
1 3 4
2
4
0
 * 
 * Output
 * 
10
 * 
 */
package com.vaani.algo.compete.hackerrank.misc;

import static java.lang.Math.min;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Matrix {

    static class Node {
        int v;
        List<Node> adj = new ArrayList<>();
        List<Integer> w = new ArrayList<>();
        boolean isMarked;
        Node(int v) { this.v = v; }
    }

    static Node[] G;
    static boolean[] visited;
    static int C;
    
    static int dfs(Node n) {
        visited[n.v] = true;
        int max = 0;
        for (int i = 0; i < n.adj.size(); i++) {
            Node a = n.adj.get(i);
            if (visited[a.v]) continue;
            int m = dfs(a);
            if (n.isMarked) {
                if (a.isMarked)
                    C += n.w.get(i);
                else
                    C += min(m, n.w.get(i));
            } else {
                if (a.isMarked)
                    m = n.w.get(i);
                else
                    m = min(m, n.w.get(i));
                if (m > max) {
                    C += max;
                    max = m;
                } else
                    C += m;
            }
        }
        if (n.isMarked) return 0;
        return max;
    }
    
    static void solve() {
        visited = new boolean[G.length];
        dfs(G[0]);
        System.out.println(C);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Matrix.class.getResourceAsStream("Matrix.in"));
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        G = new Node[n];
        Arrays.setAll(G, Node::new);
        for (int i = 0; i < n - 1; i++) {
            int v = scanner.nextInt();
            int u = scanner.nextInt();
            int w = scanner.nextInt();
            G[v].adj.add(G[u]);
            G[v].w.add(w);
            G[u].adj.add(G[v]);
            G[u].w.add(w);
        }
        IntStream.range(0, k).forEach(i -> G[scanner.nextInt()].isMarked = true);
        solve();
        scanner.close();
    }

}
