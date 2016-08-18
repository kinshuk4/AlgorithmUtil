package com.vaani.algo.ds.tree.binary;

import com.vaani.algo.ds.core.TreeNode;

/**
 * Given a binary tree, find the maximum path sum.
 * <p>
 * The path may start and end at any node in the tree.
 * <p>
 * For example:
 * Given the below binary tree,
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * Return 6.
 * <p>
 * Created by Xiaomeng on 8/13/2014.
 */
public class BinaryTreeMaximumPathSum {
    int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(20);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(40);
        root.left.right = new TreeNode(50);

        BinaryTreeMaximumPathSum pathSum = new BinaryTreeMaximumPathSum();
        System.out.println(pathSum.maxPathSum(root));
    }

    public int maxPathSum(TreeNode root) {
        dfs1(root);
        return max;
    }

    public int dfs1(TreeNode root) {
        if (root == null) return 0;
        int m = root.val;

        int left = dfs1(root.left);
        int right = dfs2(root.right);

        if (left > 0) m += left;
        if (right > 0) m += right;
        max = m > max ? m : max;

        return Math.max(left, right) > 0 ? root.val + Math.max(left, right) : root.val;
    }

    /**
     * My solution, very similar to the first solution
     */
    public int dfs2(TreeNode root) {
        if (root == null) return 0;

        int left = dfs2(root.left);
        if (left < 0) left = 0;

        int right = dfs2(root.right);
        if (right < 0) right = 0;

        int total = left + right + root.val;
        if (total > max) max = total;

        return root.val + Math.max(left, right);
    }
}
