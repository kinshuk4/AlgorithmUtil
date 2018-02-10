package com.vaani.algo.compete.cc150.chap4treegraph;

import com.vaani.algo.ds.core.TreeNode;

/**
 * Design an algorithm and write code to find the first common ancestor of two
 * nodes in a binary tree. Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 */
public class Question7 {

    public TreeNode lca(TreeNode root, TreeNode first, TreeNode second) {
        return lcaBottomUp(root, first, second);
    }

    // top-down: O(n^2) time, O(1) space
    public TreeNode lcaTopDown(TreeNode root, TreeNode first, TreeNode second) {
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

    private int countMatch(TreeNode node, TreeNode first, TreeNode second) {
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

    public TreeNode lcaBottomUp(TreeNode root, TreeNode first, TreeNode second) {
        if (root == null) {
            return null;
        }
        if (root == first || root == second) {
            return root;
        }
        TreeNode lcaLeft = lca(root.left, first, second);
        TreeNode lcaRight = lca(root.right, first, second);
        if (lcaLeft != null && lcaRight != null) { // each side finds one node
            return root;
        }
        return lcaLeft != null ? lcaLeft : lcaRight; // only one side finds both
    }



}




