package com.vaani.algo.ds.algos.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A Bipartite Graph is a graph whose adjacencyList can be divided into two independent sets, U and V such that every edge (u, v) either connects a vertex from U to V or a vertex from V to U.
 * In other words, for every edge (u, v), either u belongs to U and v to V, or u belongs to V and v to U. We can also say that there is no edge that connects adjacencyList of same set.
 * <p>
 * Reference: http://www.geeksforgeeks.org/bipartite-graph/
 */
public class IsBipartiteGraph {
    public static boolean isBipartite(int[][] adj, int src) {
        int V = adj.length;
        int[] colors = new int[V];
        for (int i = 0; i < V; i++) colors[i] = -1;

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(src);
        colors[src] = 1;

        while (queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (adj[u][v] == 1) {
                    if (colors[v] == -1) {
                        colors[v] = 1 - colors[u];
                    } else if (adj[u][v] == colors[u]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int graph[][] = {
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}
        };

        System.out.println(isBipartite(graph, 0));
    }
}
