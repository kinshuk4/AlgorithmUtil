package com.vaani.algo.cc150.chap4treegraph;

/**
 * Given a sorted (increasing order) array, write an algorithm to create a
 * binary search tree with minimal height.
 * 
 */
// O(n) space, O(n) time
public class Question3 {

  // this question is available at leetcode, question Convert Sorted Array to
  // Binary Search Tree

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public TreeNode sortedArrayToBST(int[] num) {
    // Start typing your Java solution below
    // DO NOT write main() function
    return buildTree(num, 0, num.length - 1);
  }

  private TreeNode buildTree(int[] num, int first, int last) {
    if (first > last) {
      return null;
    } else {
      int mid = (first + last) / 2;
      TreeNode node = new TreeNode(num[mid]);
      node.left = buildTree(num, first, mid - 1);
      node.right = buildTree(num, mid + 1, last);
      return node;
    }
  }
}
