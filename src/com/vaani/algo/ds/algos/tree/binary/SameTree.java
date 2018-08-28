package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 * https://github.com/xiaoningning/LeetCode-1/blob/master/Java/src/net/kenyang/algorithm/SameTree.java
 */
public class SameTree {
    public static void main(String[] args) {
        BinaryTreeNode root1 = new BinaryTreeNode(1);
        root1.left = new BinaryTreeNode(2);
        root1.right = new BinaryTreeNode(3);

        BinaryTreeNode root2 = new BinaryTreeNode(1);
        root2.left = new BinaryTreeNode(2);
        root2.right = new BinaryTreeNode(3);

        SameTree test = new SameTree();
        System.out.println(test.isSameTree(root1, root2));
    }

    public boolean isSameTree(BinaryTreeNode p, BinaryTreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null || p.val.equals(q.val))
            return false;
        else
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
