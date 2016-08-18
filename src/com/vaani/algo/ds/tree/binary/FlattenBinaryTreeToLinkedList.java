package com.vaani.algo.ds.tree.binary;

import com.vaani.algo.ds.core.TreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example,
 * Given
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * <p>
 * The flattened tree should look like:
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p>
 * Created by Xiaomeng on 8/16/2014.
 */
public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;

        flatten(root.left);
        flatten(root.right);
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null)
            root = root.right;
        root.right = right;
    }
}
