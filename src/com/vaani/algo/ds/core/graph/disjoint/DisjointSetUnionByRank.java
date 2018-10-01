package com.vaani.algo.ds.core.graph.disjoint;

import java.util.Arrays;

//https://algs4.cs.princeton.edu/15uf/UF.java.html
public class DisjointSetUnionByRank extends ADisjointSet {

    private int[] array;
    private int[] rank;

    private int numComponents;

    public DisjointSetUnionByRank(int size) {
        makeSet(size);
    }


    @Override
    void makeSet(int size) {
        numComponents = size;
        rank = new int[size];
        array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
            rank[i] = 0;
        }
    }

    /*
     * Connects j to root of i.
     * If i and j are in same group false is returned.
     */
    public boolean union(int j, int i) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI == rootJ) {
            return false;
        }
        if (rank[rootI] < rank[rootJ]) {
            array[rootI] = rootJ;
        } else if (rank[rootI] > rank[rootJ]) {
            array[rootJ] = rootI;
        } else {
            array[rootJ] = rootI;
            rank[rootI]++;
        }
        numComponents--;
        return true;
    }

    public int find(int i) {
        while (array[i] != i) {
            i = array[i];
        }
        return i;
    }

    public int getNumComponents() {
        return numComponents;
    }

    @Override
    public String toString() {
        return Arrays.toString(array).concat(Arrays.toString(rank));
    }

    public static void main(String[] args) {
        ADisjointSet disjointSet = new DisjointSetUnionByRank(6);
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
