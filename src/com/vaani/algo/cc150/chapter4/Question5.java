package com.vaani.algo.cc150.chapter4;

/**
 * Implement a function to check if a binary tree is a binary search tree.
 * 
 */
// O(1) space, O(n) time
public class Question5 {

  // this question is available at leetcode, Validating Binary Search Tree
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public boolean isValidBST(TreeNode root) {
    // Start typing your Java solution below
    // DO NOT write main() function
    int min = Integer.MIN_VALUE;
    int max = Integer.MAX_VALUE;
    return validate(root, min, max);
  }

  private boolean validate(TreeNode node, int min, int max) {
    if (node == null) {
      return true;
    }
    if (min < node.val && node.val < max) {
      return validate(node.left, min, node.val)
          && validate(node.right, node.val, max);
    } else {
      return false;
    }
  }

}
