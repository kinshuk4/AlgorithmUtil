package com.vaani.algo.compete.cc150.chap4treegraph;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Given a sorted (increasing order) array, write an algorithm to create a
 * binary search tree with minimal height.
 */
// O(n) space, O(n) time
public class Question3 {

    // this question is available at leetcode, question Convert Sorted Array to
    // Binary Search Tree

    public BinaryTreeNode sortedArrayToBST(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        return buildTree(num, 0, num.length - 1);
    }

    private BinaryTreeNode buildTree(int[] num, int first, int last) {
        if (first > last) {
            return null;
        } else {
            int mid = (first + last) / 2;
            BinaryTreeNode node = new BinaryTreeNode(num[mid]);
            node.left = buildTree(num, first, mid - 1);
            node.right = buildTree(num, mid + 1, last);
            return node;
        }
    }

}
