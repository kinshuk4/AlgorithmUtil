package com.vaani.algo.misc;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Definition for binary tree
 * public class BinaryTreeNode {
 * int val;
 * BinaryTreeNode left;
 * BinaryTreeNode right;
 * BinaryTreeNode(int x) { val = x; }
 * }
 */
public class SameTree {
    public boolean isSameTree(BinaryTreeNode p, BinaryTreeNode q) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if (p == null && q == null)
            return true;

        if (p == null || q == null)
            return false;

        if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }


    }
}
