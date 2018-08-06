package com.vaani.algo.ds.algos.tree.bst;

import com.vaani.algo.ds.core.tree.TreeNode;

public class KthSmallestInBst {
    static int kthSmallestInBST(TreeNode<Integer> t, int k) {
        return NthElementInorderTraversal.getNthIterative(t, k);
    }
}
