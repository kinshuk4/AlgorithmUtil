package com.vaani.algo.ds.algos.tree.bst;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

public class KthSmallestInBst {
    static int kthSmallestInBST(BinaryTreeNode<Integer> t, int k) {
        return NthElementInorderTraversal.getNthIterative(t, k);
    }
}
