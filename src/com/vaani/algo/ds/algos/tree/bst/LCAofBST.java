package com.vaani.algo.ds.algos.tree.bst;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Find the LCA of the binary search tree.
 */
public class LCAofBST {


    /**
     * O(logn) time, O(1) space
     */
    public class Solution {
        BinaryTreeNode LCA(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> q) {
            if (root == null) {
                return null;
            }

            if (root.val < p.val && root.val < q.val) { // smaller than both
                return LCA(root.right, p, q);
            } else if (root.val > p.val && root.val > q.val) { // bigger than both
                return LCA(root.left, p, q);
            } else {
                return root;
            }

        }
    }
}
