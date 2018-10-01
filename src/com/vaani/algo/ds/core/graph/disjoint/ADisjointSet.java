package com.vaani.algo.ds.core.graph.disjoint;

public abstract class ADisjointSet {
    protected int N;

    abstract void makeSet(int N);
    abstract boolean union(int i, int j);

    abstract int find(int i);

    boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }
}
