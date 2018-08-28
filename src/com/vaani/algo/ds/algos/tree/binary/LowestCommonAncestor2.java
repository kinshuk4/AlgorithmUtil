package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Created by Xiaomeng on 10/27/2014.
 */
public class LowestCommonAncestor2 {
    /**
     * Top-down approach: worst case O(n^2)
     * Balanced tree: O(n)
     * Degenerate tree: O(n^2)
     */
    public static BinaryTreeNode getLCA1(BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {
        if (root == null || p == null || q == null) return null;
        if (root == p || root == q) return root;

        int count = getMatchCount(root.left, p, q);
        if (count == 1)
            return root;
        else if (count == 0)
            return getLCA1(root.right, p, q);
        else
            return getLCA1(root.left, p, q);
    }

    public static int getMatchCount(BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {
        if (root == null) return 0;
        int count = (root == p || root == q) ? 1 : 0;
        return count + getMatchCount(root.left, p, q) + getMatchCount(root.right, p, q);
    }

    /**
     * Bottom-up approach: worst case O(n)
     */
    public static BinaryTreeNode getLCA2(BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {
        if (root == null || p == null || q == null) return null;
        if (root == p || root == q) return root;

        BinaryTreeNode left = getLCA2(root.left, p, q);
        BinaryTreeNode right = getLCA2(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(6);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(8);
        root.left.left = new BinaryTreeNode(0);
        root.left.right = new BinaryTreeNode(4);
        root.right.left = new BinaryTreeNode(7);
        root.right.right = new BinaryTreeNode(9);
        root.left.right.left = new BinaryTreeNode(3);
        root.left.right.right = new BinaryTreeNode(5);

        System.out.println(getLCA2(root, root.left, root.left.right.right).val);
    }
}
