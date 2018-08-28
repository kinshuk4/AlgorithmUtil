package com.vaani.algo.compete.cc150.chap4treegraph;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Implement a function to check if a binary tree is balanced. For the purposes
 * of this question, a balanced tree is defined to be a tree such that the
 * heights of the two subtrees of any node never differ by more than one.
 */
// O(1) space, O(n) time
public class Question1 {

    public boolean isBalanced(BinaryTreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        return check(root) >= 0;
    }

    private int check(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = check(node.left);
        int right = check(node.right);
        if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }

}
