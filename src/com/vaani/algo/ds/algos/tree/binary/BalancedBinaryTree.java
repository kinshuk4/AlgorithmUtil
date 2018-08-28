package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Created by Xiaomeng on 8/11/2014.
 */
public class BalancedBinaryTree {
    public boolean isBalanced(BinaryTreeNode root) {
        return getBalance(root) != -1;
    }

    public int getBalance(BinaryTreeNode root) {
        if (root == null) return 0;

        int left = getBalance(root.left);
        if (left == -1) return -1;

        int right = getBalance(root.right);
        if (right == -1) return -1;

        if (Math.abs(left - right) > 1) return -1;
        return left > right ? left + 1 : right + 1;
    }
}
