package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;

/*
Given a binary tree, find the maximum path sum.
The path may start and end at any node in the tree.
For example:
Given the below binary tree,
       1
      / \
     2   3
Return 6.
https://github.com/paopao2/Algorithm-Practice/blob/master/Binary%20Tree%20Maximum%20Path%20Sum.java
*/

/**
 * Definition for binary tree
 * public class BinaryTreeNode {
 * int val;
 * BinaryTreeNode left;
 * BinaryTreeNode right;
 * BinaryTreeNode(int x) { val = x; }
 * }
 */
public class MaximumPathSum {
    public int maxPathSum(BinaryTreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> curMax = new ArrayList<Integer>(1);
        curMax.add(Integer.MIN_VALUE);
        subPath(root, curMax);
        //getMaxNonAdjacentSum(root, curMax);
        return curMax.get(0);
    }

    public int subPath(BinaryTreeNode<Integer> root, ArrayList<Integer> curMax) {
        if (root == null) return 0;
        int left = Math.max(0, subPath(root.left, curMax));
        int right = Math.max(0, subPath(root.right, curMax));
        int max = root.val + left + right;
        //use set(0, value) can't pass large test
        curMax.add(0, Math.max(curMax.get(0), max));
        //can only return one path (left or right) for recursion use
        return root.val + Math.max(left, right);
    }
}
