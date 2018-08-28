package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Given a binary tree, find its maximum depth. </br>
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * https://github.com/xiaoningning/LeetCode-1/blob/master/Java/src/net/kenyang/algorithm/MaximumDepthOfBinaryTree.java
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(BinaryTreeNode root) {

        if (root == null) {
            return 0;
        }

        int iRightDepth = maxDepth(root.right);
        int iLefttDepth = maxDepth(root.left);
        return (iRightDepth > iLefttDepth) ? iRightDepth + 1 : iLefttDepth + 1;
    }


}
