package com.vaani.algo.ds.tree.binary;

import com.vaani.algo.ds.core.tree.TreeNode;

/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class MinimumDepthofBinaryTree {
    public int minDepth(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (root == null) {
            return 0;
        }
        return getDepth(root);
    }

    public int getDepth(TreeNode node) {
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
