package com.vaani.algo.ds.algos.tree.bst;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * <p>
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * https://github.com/xiaoningning/LeetCode-1/blob/master/Java/src/net/kenyang/algorithm/UniqueBinarySearchTrees.java
 */
public class NumOfUniqueBSTs {
    public int numTrees(int n) {

        if (n < 2) {
            return 1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += numTrees(i) * numTrees(n - i - 1);
        }
        return ans;

    }
}
