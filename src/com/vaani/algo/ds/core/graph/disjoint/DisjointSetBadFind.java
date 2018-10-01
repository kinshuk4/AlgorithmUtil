package com.vaani.algo.ds.core.graph.disjoint;

import java.util.Arrays;

public class DisjointSetBadFind extends ADisjointSet {

    int[] array;

    public DisjointSetBadFind(int size) {
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
        int rootI = find(i);
        int rootJ = find(j);
        //make x as parent of y
        array[rootJ] = rootI;
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

    private int getRootRecursive(int i) {
        //chase parent of current element until it reaches root
        if(i != array[i]){
            return getRoot(i);
        }
        return i;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) {
        // set1 = 2 - 0 - 1 - 3
        // set2 = 4 - 5
        ADisjointSet dsbfSet = new DisjointSetBadFind(6);
        dsbfSet.union(2, 0);
        dsbfSet.union(0, 1);
        dsbfSet.union(1, 3);
        dsbfSet.union(4, 5);

        System.out.println(dsbfSet.find(1));
        System.out.println(dsbfSet.find(5));
        System.out.println(dsbfSet.find(2));

        System.out.println(dsbfSet.isConnected(1, 5));
        System.out.println(dsbfSet.isConnected(1, 2));
        //[2, 2, 2, 2, 4, 4]
        System.out.println(dsbfSet.toString());
    }
}
