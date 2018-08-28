package com.vaani.algo.ds.algos.tree.bst;

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
public class PathSum {
    public boolean hasPathSum(BinaryTreeNode root, int sum) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (root == null) {
            return false;
        }
        return helper(root, 0, sum);
    }

    public boolean helper(BinaryTreeNode<Integer> root, int curSum, int target) {
        if (root == null) {
            return curSum == target;
        }
        if (root.left == null && root.right == null) {
            return helper(root.left, curSum + root.val, target) && helper(root.right, curSum + root.val, target);
        } else if (root.left == null) {
            return helper(root.right, curSum + root.val, target);
        } else if (root.right == null) {
            return helper(root.left, curSum + root.val, target);
        } else {
            return helper(root.left, curSum + root.val, target) || helper(root.right, curSum + root.val, target);
        }
    }
}
