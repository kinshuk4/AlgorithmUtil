package com.vaani.algo.cc150.chapter4;

/**
 * Implement a function to check if a binary tree is balanced. For the purposes
 * of this question, a balanced tree is defined to be a tree such that the
 * heights of the two subtrees of any node never differ by more than one.
 * 
 */
// O(1) space, O(n) time
public class Question1 {

  // this question is available at leetcode, question Balanced Binary Tree
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public boolean isBalanced(TreeNode root) {
    // Start typing your Java solution below
    // DO NOT write main() function
    return check(root) >= 0;
  }

  private int check(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int left = check(node.left);
    int right = check(node.right);
    if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
      return -1;
    } else {
      return Math.max(left, right) + 1;
    }
  }
}
