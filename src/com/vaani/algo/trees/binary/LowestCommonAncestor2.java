package com.vaani.algo.trees.binary;

import com.leetcode.core.TreeNode;

/**
 * Created by Xiaomeng on 10/27/2014.
 */
public class LowestCommonAncestor2 {
    /**
     * Top-down approach: worst case O(n^2)
     * Balanced tree: O(n)
     * Degenerate tree: O(n^2)
     *
     */
    public static TreeNode getLCA1(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;
        if(root == p || root == q) return root;

        int count = getMatchCount(root.left, p, q);
        if(count == 1)
            return root;
        else if(count == 0)
            return getLCA1(root.right, p, q);
        else
            return getLCA1(root.left, p, q);
    }

    public static int getMatchCount(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return 0;
        int count = (root == p || root == q) ? 1 : 0;
        return count + getMatchCount(root.left, p, q) + getMatchCount(root.right, p, q);
    }

    /**
     * Bottom-up approach: worst case O(n)
     *
     */
    public static TreeNode getLCA2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;
        if(root == p || root == q) return root;

        TreeNode left = getLCA2(root.left, p, q);
        TreeNode right = getLCA2(root.right, p, q);
        if(left != null && right != null) return root;
        return left != null ? left : right;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        System.out.println(getLCA2(root, root.left, root.left.right.right).val);
    }
}
