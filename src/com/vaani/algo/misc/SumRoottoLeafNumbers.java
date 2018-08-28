package com.vaani.algo.misc;

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
public class SumRoottoLeafNumbers {
    int sum;

    public int sumNumbers(BinaryTreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        sum = 0;
        if (root == null) {
            return sum;
        }
        helper(root, 0);
        return sum;
    }

    public void helper(BinaryTreeNode<Integer> root, int curSum) {
        curSum = curSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum = sum + curSum;
        }
        if (root.left != null) {
            helper(root.left, curSum);
        }
        if (root.right != null) {
            helper(root.right, curSum);
        }
    }
}
