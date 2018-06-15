package com.vaani.algo.array;

import java.util.*;


/**
 * You are given an array parent of length n specifying a tree. The vertices of the tree are numbered from 0 to n - 1 and parent[i] is the parent of the ith node. The root of the tree is the vertex v, the parent of which is the same vertex (i.e. parent[v] = v if and only if v is a root).
 * <p>
 * What will the parent array look like if the edges remain the same but tree is rooted at the other vertex newRoot?
 * <p>
 * Example
 * <p>
 * For parent = [0, 0, 0, 1] and newRoot = 1, the output should be
 * changeRoot(parent, newRoot) = [1, 1, 0, 1].
 * <p>
 * Check out the image below for better understanding
 */
public class ChangeRoot {
    static int[] changeRoot(int[] parent, int newRoot) {
        int n = parent.length;
        int currRoot = 0;

        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                currRoot = i;
                break;
            }
        }

        int currParentOfNewRoot = parent[newRoot];
        parent[newRoot] = newRoot;

        int tempRoot = newRoot;
        while (currParentOfNewRoot != currRoot){
            int temp = parent[currParentOfNewRoot];
            parent[currParentOfNewRoot] = tempRoot;
            tempRoot = currParentOfNewRoot;
            currParentOfNewRoot = temp;
        }

        if(currParentOfNewRoot == currRoot){
            parent[currRoot] = tempRoot;
        }

        return parent;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 0, 1};
        System.out.println(Arrays.toString(changeRoot(arr,1)));
    }

}
