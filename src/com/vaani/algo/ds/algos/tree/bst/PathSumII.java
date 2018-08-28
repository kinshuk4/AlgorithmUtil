package com.vaani.algo.ds.algos.tree.bst;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;

/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
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
public class PathSumII {
    ArrayList<ArrayList<Integer>> result;

    public ArrayList<ArrayList<Integer>> pathSum(BinaryTreeNode root, int sum) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        result = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return result;
        }
        helper(root, 0, sum, new ArrayList<Integer>());
        return result;
    }

    public void helper(BinaryTreeNode<Integer> root, int curSum, int target, ArrayList<Integer> list) {
        ArrayList<Integer> curList = new ArrayList<Integer>(list);
        if (root == null) {
            return;
        }
        curList.add(root.val);
        if (root.left == null && root.right == null) {
            if (curSum + root.val == target) {
                result.add(curList);
            }
        }

        helper(root.left, curSum + root.val, target, curList);
        helper(root.right, curSum + root.val, target, curList);

    }
}
