package com.vaani.algo.ds.tree.binary;


import com.vaani.algo.ds.core.tree.TreeNode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * For example, this binary tree is symmetric:
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * <p>
 * But the following is not:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * <p>
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 * https://github.com/xiaoningning/LeetCode-1/blob/master/Java/src/net/kenyang/algorithm/SymmetricTree.java
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode leftNode, TreeNode rightNode) {
        if (rightNode == null && leftNode == null)
            return true;
        if (rightNode == null)
            return false;
        if (leftNode == null)
            return false;


        return leftNode.val == rightNode.val
                && isSymmetric(leftNode.left, rightNode.right)
                && isSymmetric(leftNode.right, rightNode.left);

    }
}