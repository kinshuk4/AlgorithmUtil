package com.vaani.algo.ds.algos.tree.binary;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * <p>
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * Created by Xiaomeng on 8/14/2014.
 */
public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        UniqueBinarySearchTrees test = new UniqueBinarySearchTrees();
        System.out.println(test.numTrees2(4));
    }

    public int numTrees(int n) {
        int[] count = new int[n + 1];
        count[0] = 1;
        count[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }

    public int numTrees2(int n) {
        if (n == 0 || n == 1) return 1;
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += numTrees2(i) * numTrees2(n - i - 1);
        }
        return result;
    }
}
