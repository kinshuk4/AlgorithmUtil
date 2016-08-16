package com.vaani.algo.tree.bst;

/**
 * Find the LCA of the binary search tree.
 * 
 */
public class LCAofBST {
  
  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
      val = value;
      left = null;
      right = null;
    }
  }

  /**
   * O(logn) time, O(1) space 
   *
   */
  public class Solution {
    TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
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
