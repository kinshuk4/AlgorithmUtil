package com.vaani.algo.compete.cc150.chap4treegraph;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Design an algorithm and write code to find the first common ancestor of two
 * nodes in a binary tree. Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 */
public class Question7 {

    public BinaryTreeNode lca(BinaryTreeNode root, BinaryTreeNode first, BinaryTreeNode second) {
        return lcaBottomUp(root, first, second);
    }

    // top-down: O(n^2) time, O(1) space
    public BinaryTreeNode lcaTopDown(BinaryTreeNode root, BinaryTreeNode first, BinaryTreeNode second) {
        // write implementation here
        if (root == null || first == null || second == null) {
            return null;
        }
        if (root == first || root == second) {
            return root;
        }
        int matches = countMatch(root.left, first, second);
        if (matches == 0) { // all on the right
            return lcaTopDown(root.right, first, second);
        } else if (matches == 1) {
            return root;
        } else { // on the right
            return lcaTopDown(root.left, first, second);
        }
    }

    private int countMatch(BinaryTreeNode node, BinaryTreeNode first, BinaryTreeNode second) {
        if (node == null) {
            return 0;
        }
        int matches = countMatch(node.left, first, second) + countMatch(node.right, first, second);
        if (node == first || node == second) {
            return 1 + matches;
        } else {
            return matches;
        }
    }

    public BinaryTreeNode lcaBottomUp(BinaryTreeNode root, BinaryTreeNode first, BinaryTreeNode second) {
        if (root == null) {
            return null;
        }
        if (root == first || root == second) {
            return root;
        }
        BinaryTreeNode lcaLeft = lca(root.left, first, second);
        BinaryTreeNode lcaRight = lca(root.right, first, second);
        if (lcaLeft != null && lcaRight != null) { // each side finds one node
            return root;
        }
        return lcaLeft != null ? lcaLeft : lcaRight; // only one side finds both
    }



}




