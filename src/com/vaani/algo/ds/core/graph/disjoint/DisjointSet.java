package com.vaani.algo.ds.core.graph.disjoint;

import java.util.Arrays;

public class DisjointSet extends ADisjointSet {

    int[] disjointSet;

    int[] BT;

    public DisjointSet(int size) {
        makeSet(size);
    }


    @Override
    void makeSet(int size) {
        BT = new int[size];
        disjointSet = new int[size];
        for (int i = 0; i < disjointSet.length; i++)
            disjointSet[i] = i;
    }

    /*
     * Connects j to root of i.
     * If i and j are in same group false is returned.
     */
    public boolean union(int j, int i) {
        int c = 0;
        while (disjointSet[i] != i) {
            BT[c++] = i;
            i = disjointSet[i];
        }
        while (disjointSet[j] != j) {
            int t = j;
            j = disjointSet[j];
            disjointSet[t] = i;
        }
        disjointSet[j] = i;
        for (int k = 0; k < c; k++)
            disjointSet[BT[k]] = i;
        return i != j;
    }

    public int find(int i) {
        while (disjointSet[i] != i) {
            i = disjointSet[i];
        }
        return i;
    }

    @Override
    public String toString() {
        return Arrays.toString(disjointSet).concat(Arrays.toString(BT));
    }

    public static void main(String[] args) {
        ADisjointSet disjointSet = new DisjointSet(6);
        disjointSet.union(2, 0);
        disjointSet.union(0, 1);
        disjointSet.union(1, 3);
        disjointSet.union(4, 5);

        System.out.println(disjointSet.find(1));
        System.out.println(disjointSet.find(5));
        System.out.println(disjointSet.find(2));

        System.out.println(disjointSet.isConnected(1, 5));
        System.out.println(disjointSet.isConnected(1, 2));
        //[2, 2, 2, 2, 4, 4]
        System.out.println(disjointSet.toString());
    }
}
