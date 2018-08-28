package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

public class IsSameTree {
    static boolean isSameTree(BinaryTreeNode<Integer> t1, BinaryTreeNode<Integer> t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return t1 == t2;
        }
        if (!t1.val.equals(t2.val)) {
            return false;
        }
        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }
}
