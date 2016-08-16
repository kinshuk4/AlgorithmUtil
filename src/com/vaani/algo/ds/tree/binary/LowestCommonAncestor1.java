package com.vaani.algo.ds.tree.binary;

import com.leetcode.core.TreeNode;

/**
 * Given a binary search tree (BST), find the lowest common ancestor of two given nodes in the BST.
 *
 * Created by Xiaomeng on 10/26/2014.
 */
public class LowestCommonAncestor1 {
    /**
     * O(logN) Time
     * Reference: http://leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-search-tree.html
     *
     */
    public static TreeNode getLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;
        if (root.val > Math.max(p.val, q.val)) {
            return getLCA(root.left, p, q);
        } else if (root.val < Math.min(p.val, q.val)) {
            return getLCA(root.right, p, q);
        } else {
            return root;
        }
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

        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);
        System.out.println(getLCA(root, p, q).val);
    }
}
