package com.vaani.algo.ds.algos.tree.binary;

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
public class MinimumDepthofBinaryTree {
    public int minDepth(BinaryTreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (root == null) {
            return 0;
        }
        return getDepth(root);
    }

    public int getDepth(BinaryTreeNode node) {
        if (node.left == null && node.right == null) {
            return 1;
        } else if (node.left == null) {
            return getDepth(node.right) + 1;
        } else if (node.right == null) {
            return getDepth(node.left) + 1;
        } else {
            return Math.min(getDepth(node.left), getDepth(node.right)) + 1;
        }
    }
}
