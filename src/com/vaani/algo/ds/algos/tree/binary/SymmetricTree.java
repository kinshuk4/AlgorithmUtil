package com.vaani.algo.ds.algos.tree.binary;


import com.vaani.algo.ds.core.tree.BinaryTreeNode;

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
 */
public class SymmetricTree {

    public boolean isSymmetric(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetricHelper(root.left, root.right);
    }

    public boolean isSymmetricHelper(BinaryTreeNode left, BinaryTreeNode right) {
        if (right == null && left == null)
            return true;
        if (right == null || left == null)
            return false;

        //Note using equals method and not == as otherwise for generic objects it checkes equality of reference.
        return left.val.equals(right.val)
                && isSymmetricHelper(left.left, right.right)
                && isSymmetricHelper(left.right, right.left);

    }


    public boolean isSymmetricHelper2(BinaryTreeNode n1, BinaryTreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 == null || n2 == null || n1.val != n2.val) {
            return false;
        } else {
            return isSymmetricHelper2(n1.left, n2.right) && isSymmetricHelper2(n1.right, n2.left);

        }
    }
}