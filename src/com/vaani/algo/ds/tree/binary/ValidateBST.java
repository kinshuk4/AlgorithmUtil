package com.vaani.algo.ds.tree.binary;

import com.vaani.algo.ds.core.TreeNode;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * Created by Xiaomeng on 8/9/2014.
 */
public class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, int min, int max) {
        if (root == null) return true;
        if (root.val > min && root.val < max
                && isValidBST(root.left, min, root.val)
                && isValidBST(root.right, root.val, max)) {
            return true;
        } else {
            return false;
        }
    }
}
