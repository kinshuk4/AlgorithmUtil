package com.vaani.algo.paradigm.recursion;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Find the closest element in a BST.
 */
public class ClosestInBst {

    public int closest(BinaryTreeNode<Integer> root, int target) {
        int[] res = {-1};
        int[] diff = {Integer.MAX_VALUE};

        closest(root, target, res, diff);
        return res[0];
    }

    private void closest(BinaryTreeNode<Integer> node, int target, int[] curRes, int[] diff) {

        if (node == null) {
            return;
        }

        int curDiff = Math.abs(node.val - target);
        if (node.val == target) {
            curRes[0] = node.val;
            diff[0] = 0;
        } else if (node.val < target) { // search the right sub-tree
            if (curDiff < diff[0]) {
                curRes[0] = node.val;
                diff[0] = curDiff;
            }
            closest(node.right, target, curRes, diff);
        } else { // search the left sub-tree
            if (curDiff < diff[0]) {
                curRes[0] = node.val;
                diff[0] = curDiff;
            }
            closest(node.left, target, curRes, diff);
        }
    }

}
