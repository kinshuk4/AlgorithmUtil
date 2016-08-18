package com.vaani.algo.ds.tree.binary;

/**
 * Given a binary tree, find its maximum depth. </br>
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * https://github.com/xiaoningning/LeetCode-1/blob/master/Java/src/net/kenyang/algorithm/MaximumDepthOfBinaryTree.java
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int iRightDepth = maxDepth(root.right);
        int iLefttDepth = maxDepth(root.left);
        return (iRightDepth > iLefttDepth) ? iRightDepth + 1 : iLefttDepth + 1;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
