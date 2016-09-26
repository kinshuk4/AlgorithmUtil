package com.vaani.algo.compete.cc150.chap4treegraph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a directed graph, design an algorithm to find out whether there is a
 * route between two nodes.
 */
// O(1) Space, O(V + E) time
public class Question2 {

    public boolean isRouteBetween(DirectedGraph g, int nodeIdx1, int nodeIdx2) {
        // write implementation here
        return reachable(g, nodeIdx1, nodeIdx2) || reachable(g, nodeIdx2, nodeIdx1);
    }

    private boolean reachable(DirectedGraph g, int from, int to) {
        boolean[] visited = new boolean[g.V()];
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(from);

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            visited[cur] = true;
            Iterator<Integer> neighbors = g.adj(cur).iterator();
            while (neighbors.hasNext()) {
                int neighbor = neighbors.next();
                if (!visited[neighbor]) {
                    if (neighbor == to) {
                        return true;
                    }
                    stack.push(neighbor);
                }
            }
        }

        return false;
    }

    public static class DirectedGraph {

        private int v;
        private LinkedList<Integer> edges[];

        public DirectedGraph(int nodeSize) {
            this.v = nodeSize;
            this.edges = (LinkedList<Integer>[]) new LinkedList[v];
            for (int i = 0; i < v; ++i) {
                this.edges[i] = new LinkedList<Integer>();
            }
        }

        public void addEdge(int v, int w) {
            this.edges[v].add(w);
        }

        public Iterable<Integer> adj(int v) {
            return edges[v];
        }

        public int V() {
            return v;
        }

        public int E() {
            int e = 0;
            for (LinkedList<Integer> edge : edges)
                e += edge.size();
            return e;
        }

    }

}
