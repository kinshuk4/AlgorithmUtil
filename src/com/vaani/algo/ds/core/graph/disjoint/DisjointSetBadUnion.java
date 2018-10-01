package com.vaani.algo.ds.core.graph.disjoint;

import java.util.Arrays;

public class DisjointSetBadUnion extends ADisjointSet {

    int[] array;

    public DisjointSetBadUnion(int size) {
        makeSet(size);
    }

    @Override
    void makeSet(int N) {
        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = i;
        }
    }

    @Override
    public boolean union(int i, int j) {
        int temp = array[i];
        for (int k = 0; k < array.length; k++) {
            if (array[k] == temp) {
                array[k] = array[j];
            }
        }
        return true;
    }

    @Override
    public int find(int i) {
        return array[i];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) {
        // set1 = 2 - 0 - 1 - 3
        // set2 = 4 - 5
        ADisjointSet dsbuSet = new DisjointSetBadUnion(6);
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
