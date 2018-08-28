package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/*
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
*/

/**
 * Definition for binary tree
 * public class BinaryTreeNode {
 * int val;
 * BinaryTreeNode left;
 * BinaryTreeNode right;
 * BinaryTreeNode(int x) { val = x; }
 * }
 */
public class CheckBinaryTreeBalanced {
    public boolean isBalanced(BinaryTreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (root == null) {
            return true;
        }
        int diff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(diff) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int getHeight(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getHeight(node.left) + 1;
        int right = getHeight(node.right) + 1;
        return left > right ? left : right;
    }
}