package com.vaani.algo.ds.core.graph.disjoint;

import java.util.Arrays;

public class DisjointSetWeightedUnion extends ADisjointSet {

    int[] array;
    int[] size;

    public DisjointSetWeightedUnion(int size) {
        makeSet(size);
    }

    @Override
    void makeSet(int N) {
        array = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public boolean union(int i, int j) {
        return weightedUnion(i, j);
    }

    protected boolean weightedUnion(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (size[rootI] < size[rootJ]) {
            array[rootI] = array[rootJ];
            size[rootJ] += size[rootI];
        } else {
            array[rootJ] = array[rootI];
            size[rootI] += size[rootJ];
        }
        return true;
    }

    @Override
    public int find(int i) {
        return getRoot(i);
    }

    //Get root is nothing but find...just adding a new method to show we are getting root.
    private int getRoot(int i) {
        //chase parent of current element until it reaches root
        while (array[i] != i) {
            i = array[i];
        }
        return i;
    }

    @Override
    public String toString() {
        return Arrays.toString(array).concat(Arrays.toString(size));
    }

    public static void main(String[] args) {
        // set1 = 2 - 0 - 1 - 3
        // set2 = 4 - 5
        ADisjointSet dsbuSet = new DisjointSetWeightedUnion(6);
        dsbuSet.union(2, 0);
        dsbuSet.union(0, 1);
        dsbuSet.union(1, 3);
        dsbuSet.union(4, 5);

        System.out.println(dsbuSet.find(1));
        System.out.println(dsbuSet.find(5));
        System.out.println(dsbuSet.find(2));

        System.out.println(dsbuSet.isConnected(1, 5));
        System.out.println(dsbuSet.isConnected(1, 2));
        //[3, 3, 3, 3, 5, 5]
        System.out.println(dsbuSet.toString());
    }
}
